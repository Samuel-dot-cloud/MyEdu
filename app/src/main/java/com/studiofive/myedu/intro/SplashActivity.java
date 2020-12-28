package com.studiofive.myedu.intro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.studiofive.myedu.MainActivity;
import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.Category;
import com.studiofive.myedu.classes.ExamCategory;
import com.studiofive.myedu.fragments.HighSchoolFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIMER = 3000;

    @BindView(R.id.background_image)
    GifImageView mBackgroundImage;
    @BindView(R.id.powered_by_line)
    TextView mPoweredBy;

    public static List<Category> categoryList = new ArrayList<>();
    public static List<ExamCategory> examCategoryList = new ArrayList<>();
    public static int selected_category_index = 0;
    private FirebaseFirestore mFirestore;
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mFirestore = FirebaseFirestore.getInstance();
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        mBackgroundImage.setAnimation(sideAnim);
        mPoweredBy.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Load Preschool data first
                loadData();
                loadExamCategories();
            }
        }, SPLASH_TIMER);
    }

    private void loadData() {
        categoryList.clear();
        mFirestore.collection("PreQuiz").document("Categories")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        long count = (long) documentSnapshot.get("Count");

                        for (int i = 1; i< count + 1; i++){
                            String categoryName = documentSnapshot.getString("Cat" + String.valueOf(i) + "_Name");
                            String categoryID = documentSnapshot.getString("Cat" + String.valueOf(i) + "_ID");
                            categoryList.add(new Category(categoryID, categoryName));

                        }

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);

                    }else {
                        Toasty.error(SplashActivity.this, "Something went wrong loading categories!!!", Toast.LENGTH_SHORT, true).show();
                    }
                    finish();
                }else {
                    Toasty.error(SplashActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }




    private void loadExamCategories(){
        examCategoryList.clear();
        mFirestore.collection("Exams").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, QueryDocumentSnapshot> documentList = new ArrayMap<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                            documentList.put(doc.getId(), doc);
                        }

                        QueryDocumentSnapshot categoryListDoc = documentList.get("Categories");
                        long catCount = categoryListDoc.getLong("Count");

                        for (int i = 1; i <= catCount; i++){
                            String catID = categoryListDoc.getString("Cat" + String.valueOf(i) + "_ID");
                            QueryDocumentSnapshot catDoc = documentList.get(catID);
                            int noOfTests = catDoc.getLong("No_Of_Tests").intValue();
                            String catName = catDoc.getString("Name");
                            examCategoryList.add(new ExamCategory(catID, catName, noOfTests));

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.error(SplashActivity.this , e.getMessage(), Toast.LENGTH_SHORT,true).show();
                    }
                });
    }

}