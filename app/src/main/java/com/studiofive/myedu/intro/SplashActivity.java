package com.studiofive.myedu.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.MainActivity;
import com.studiofive.myedu.R;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> categoryList = new ArrayList<>();
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

                        for (int i = 1; i< count; i++){
                            String categoryName = documentSnapshot.getString("Cat" + String.valueOf(i));
                            categoryList.add(categoryName);

                        }

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toasty.error(SplashActivity.this, "Something went wrong loading categories!!!", Toast.LENGTH_SHORT, true).show();
                        finish();
                    }
                }else {
                    Toasty.error(SplashActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }



}