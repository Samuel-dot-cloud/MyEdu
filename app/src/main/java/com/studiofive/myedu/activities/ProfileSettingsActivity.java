package com.studiofive.myedu.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.studiofive.myedu.R;
import com.studiofive.myedu.activities.views.FullScreenActivity;
import com.studiofive.myedu.classes.Common;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class ProfileSettingsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.set_profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.set_user_name)
    EditText mUserName;
    @BindView(R.id.set_personal_mantra)
    EditText mPersonalMantra;
//    @BindView(R.id.update_settings_button)
//    Button mUpdateProfileSettings;
    @BindView(R.id.fab_camera)
    FloatingActionButton cameraButton;

    private FirebaseUser mFirebaseUser;
    private FirebaseFirestore mFirestore;
    private Uri imageUri;
    private ProgressDialog mProgressDialog;
    private StorageReference mImageRef;
    private BottomSheetDialog bottomSheetMantra, bottomSheetEditName;
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

        initActionClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseUser!=null){
            getInfo();
        }
    }

    private void initActionClick() {
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_PIC);
            }
        });

        mUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetEditName();
            }
        });

        mPersonalMantra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetEditMantra();
            }
        });

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfileImage.invalidate();
                Drawable dr = mProfileImage.getDrawable();
                Common.IMAGE_BITMAP = ((BitmapDrawable)dr.getCurrent()).getBitmap();
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ProfileSettingsActivity.this, mProfileImage, "image");
                Intent intent = new Intent(ProfileSettingsActivity.this, FullScreenActivity.class);
                startActivity(intent, activityOptionsCompat.toBundle());
            }
        });
    }

    private void showBottomSheetEditMantra() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_edit_mantra, null);

        ((View) view.findViewById(R.id.btn_cancel_mantra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetMantra.dismiss();
            }
        });

        final EditText editPersonalMantra = view.findViewById(R.id.ed_personal_mantra);

        ((View) view.findViewById(R.id.btn_save_mantra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editPersonalMantra.getText().toString())){
                    Toasty.warning(getApplicationContext(), "Personal mantra cannot be blank", Toast.LENGTH_SHORT);
                }else{
                    updatePersonalMantra(editPersonalMantra.getText().toString());
                    bottomSheetMantra.dismiss();
                }
            }
        });

        bottomSheetMantra = new BottomSheetDialog(this);
        bottomSheetMantra.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Objects.requireNonNull(bottomSheetMantra.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bottomSheetMantra.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bottomSheetMantra = null;
            }
        });

        bottomSheetMantra.show();
    }

    private void showBottomSheetEditName() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_edit_name, null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetEditName.dismiss();
            }
        });

        final EditText editUserName = view.findViewById(R.id.ed_username);

        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editUserName.getText().toString())){
                    Toasty.warning(getApplicationContext(), "Name cannot be blank", Toast.LENGTH_SHORT);
                }else{
                    updateName(editUserName.getText().toString());
                    bottomSheetEditName.dismiss();
                }
            }
        });

        bottomSheetEditName = new BottomSheetDialog(this);
        bottomSheetEditName.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Objects.requireNonNull(bottomSheetEditName.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bottomSheetEditName.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bottomSheetEditName = null;
            }
        });

        bottomSheetEditName.show();
    }

    private void getInfo() {
        mFirestore.collection("Users").document(mFirebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userName = documentSnapshot.getString("userName");
                String personalMantra = documentSnapshot.getString("personalMantra");
                String imageProfile = documentSnapshot.getString("profileImage");

                mUserName.setText(userName);
                mPersonalMantra.setText(personalMantra);
                Glide.with(ProfileSettingsActivity.this).load(imageProfile).into(mProfileImage);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT, true).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PIC && resultCode == RESULT_OK && data != null && data.getData() != null){
             imageUri = data.getData();

            // start picker to get image for cropping and then use the image in cropping activity
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mProgressDialog.setTitle("Setting Profile Image:");
                mProgressDialog.setMessage("Please wait while profile image is updating...");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                final Uri resultUri = result.getUri();
                StorageReference filePath = mImageRef.child(System.currentTimeMillis()+"."+ getFileExtension(resultUri));
                filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();

                        final String downloadPic_url = String.valueOf(downloadUrl);

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("profileImage", downloadPic_url);

                        mProgressDialog.dismiss();
                        mFirestore.collection("Users").document(mFirebaseUser.getUid()).update(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toasty.success(getApplicationContext(), "Upload successful", Toast.LENGTH_SHORT, true).show();
                                        getInfo();
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.error(getApplicationContext(), "Upload failed!!", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    }
                });
            }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toasty.error(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }




    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void updateName(String newName){
        mFirestore.collection("Users").document(mFirebaseUser.getUid()).update("userName", newName).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toasty.success(getApplicationContext(), "Update successful", Toast.LENGTH_SHORT, true).show();
                getInfo();
            }
        });
    }

    private void updatePersonalMantra(String newMantra){
        mFirestore.collection("Users").document(mFirebaseUser.getUid()).update("personalMantra", newMantra).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toasty.success(getApplicationContext(), "Update successful", Toast.LENGTH_SHORT, true).show();
                getInfo();
            }
        });
    }
}