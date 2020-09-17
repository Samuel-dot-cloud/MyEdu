package com.studiofive.myedu.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.MainActivity;
import com.studiofive.myedu.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_surface)
    LinearLayout mProfileSurface;
    @BindView(R.id.image_profile)
    CircleImageView mImageProfile;
    @BindView(R.id.text_username)
    TextView mUserName;
    @BindView(R.id.text_personal_mantra)
    TextView mPersonalMantra;

    private FirebaseUser mFirebaseUser;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Settings");

        mFirestore = FirebaseFirestore.getInstance();
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        initActionClick();

    }

    private void initActionClick() {
        mProfileSurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseUser!=null){
            getInfo();
        }
    }

    private void getInfo() {
        mFirestore.collection("Users").document(mFirebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userName = Objects.requireNonNull(documentSnapshot.get("userName")).toString();
                String profileImage = documentSnapshot.getString("profileImage");
                String personalMantra = Objects.requireNonNull(documentSnapshot.get("personalMantra")).toString();
                mUserName.setText(userName);
                mPersonalMantra.setText(personalMantra);
                Glide.with(SettingsActivity.this).load(profileImage).into(mImageProfile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(SettingsActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}