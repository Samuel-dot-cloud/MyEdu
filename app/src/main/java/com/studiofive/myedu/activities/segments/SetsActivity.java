package com.studiofive.myedu.activities.segments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.SetsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SetsActivity extends AppCompatActivity {
//    @BindView(R.id.category_expanded_image)
//    ImageView expandedImage;
    @BindView(R.id.category_expanded_title)
    TextView expandedTitle;
    @BindView(R.id.sets_grid_view)
    GridView setsView;

    private FirebaseFirestore mFirestore;
    private int categoryId;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
        ButterKnife.bind(this);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Category");
         categoryId = intent.getExtras().getInt("category_ID", 1);

        expandedTitle.setText(Title);
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
        mFirestore.collection("PreQuiz").document("Cat" + String.valueOf(categoryId))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        long sets = (long) documentSnapshot.get("Sets");

                        SetsAdapter setsAdapter = new SetsAdapter((int) sets);
                        setsView.setAdapter(setsAdapter);

                        loadingDialog.cancel();
                    }else {
                        Toasty.error(SetsActivity.this, "Something went wrong loading sets!!!", Toast.LENGTH_SHORT, true).show();
                        finish();
                    }
                }else {
                    Toasty.error(SetsActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }
}