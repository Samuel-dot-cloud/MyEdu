package com.studiofive.myedu.activities.segments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.SetsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static com.studiofive.myedu.intro.SplashActivity.categoryList;
import static com.studiofive.myedu.intro.SplashActivity.selected_category_index;

public class SetsActivity extends AppCompatActivity {
    //    @BindView(R.id.category_expanded_image)
//    ImageView expandedImage;
    @BindView(R.id.category_expanded_title)
    TextView expandedTitle;
    @BindView(R.id.sets_grid_view)
    GridView setsView;

    private FirebaseFirestore mFirestore;
    private Dialog loadingDialog;
    public static List<String> setsIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
        ButterKnife.bind(this);

        expandedTitle.setText(categoryList.get(selected_category_index).getName());
//        expandedImage.setImageResource(image);

        loadingDialog = new Dialog(SetsActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        mFirestore = FirebaseFirestore.getInstance();

        loadSets();
    }

    private void loadSets() {

        setsIDs.clear();
        mFirestore.collection("PreQuiz").document(categoryList.get(selected_category_index).getId())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                long noOfSets = (long) documentSnapshot.get("Sets");
                for (int i = 1; i < noOfSets + 1; i++) {
                    setsIDs.add(documentSnapshot.getString("Set" + String.valueOf(i) + "_ID"));
                }

                SetsAdapter setsAdapter = new SetsAdapter(setsIDs.size());
                setsView.setAdapter(setsAdapter);

                loadingDialog.dismiss();

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loadingDialog.dismiss();
                        Toasty.error(SetsActivity.this, e.getMessage(), Toast.LENGTH_SHORT, true).show();
                    }
                });


    }
}