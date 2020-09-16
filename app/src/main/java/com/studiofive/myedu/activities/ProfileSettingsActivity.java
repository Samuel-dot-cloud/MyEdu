package com.studiofive.myedu.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.studiofive.myedu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileSettingsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.set_profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.set_user_name)
    EditText mUserName;
    @BindView(R.id.set_personal_mantra)
    EditText mPersonalMantra;
    @BindView(R.id.update_settings_button)
    Button mUpdateProfileSettings;

    private FirebaseUser mFirebaseUser;
    private FirebaseFirestore mFirestore;
    private Uri imageUri;
    private ProgressDialog mProgressDialog;
    private StorageReference mImageRef;
    private static final int GALLERY_PIC = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile Settings");

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        mImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        mProgressDialog = new ProgressDialog(this);
    }
}