package com.studiofive.myedu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.GlideApp;

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
    @BindView(R.id.ln_invite_friend)
    LinearLayout mInviteSurface;
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

        mInviteSurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInviteFriends();
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
                GlideApp.with(SettingsActivity.this).load(profileImage).placeholder(R.drawable.profile_pic).into(mImageProfile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(SettingsActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    //Show invite friends message
    public void showInviteFriends() {
        //Define the GooglePlay share url
        String playerShareURL = "https://play.google.com/store/apps/details?id=com.studiofive.myedu";

        //Open sharing option
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        //What we going to share
        String shareBody = getResources().getString(R.string.msg_invite1) + "\r\n" + playerShareURL;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent,getResources().getString(R.string.msg_invite2)));
    }
}