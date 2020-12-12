package com.studiofive.myedu.activities;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
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

import java.io.File;
import java.util.HashMap;

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
    @BindView(R.id.update_settings_button)
    Button mUpdateProfileSettings;
    @BindView(R.id.fab_camera)
    FloatingActionButton cameraButton;

    private FirebaseUser mFirebaseUser;
    private FirebaseFirestore mFirestore;
    private Uri imageUri;
    private ProgressDialog mProgressDialog;
    private StorageReference mImageRef;
    private BottomSheetDialog bottomSheetMantra, bottomSheetEditName;
    private static final int GALLERY_PIC = 5;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Profile Settings");

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        currentUserId = mFirebaseUser.getUid();
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

        mUpdateProfileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextFields();
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

    private void getInfo() {
        mFirestore.collection("Users").document(mFirebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userName = documentSnapshot.getString("userName");
                String personalMantra = documentSnapshot.getString("personalMantra");
                String imageProfile = documentSnapshot.getString("profileImage");

                mUserName.setText(userName);
                mPersonalMantra.setText(personalMantra);
                Glide.with(ProfileSettingsActivity.this).load(imageProfile).placeholder(R.drawable.profile_pic).into(mProfileImage);

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
                StorageReference filePath = mImageRef.child(currentUserId +"."+ getMimeType(getApplicationContext(), resultUri));
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

    private static String getMimeType(Context context, Uri uri) {
        String extension;
        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
        }
        return extension;
    }


    private void updateTextFields(){
        String setName = mUserName.getText().toString();
        String setMantra = mPersonalMantra.getText().toString();

        if (TextUtils.isEmpty(setName) || TextUtils.isEmpty(setMantra)){
            Toasty.warning(ProfileSettingsActivity.this, "Please input profile details!!", Toast.LENGTH_SHORT, true).show();
        } else {
            HashMap<String, Object> profileMap = new HashMap<>();
            profileMap.put("userName", setName);
            profileMap.put("personalMantra", setMantra);

            mFirestore.collection("Users").document(mFirebaseUser.getUid()).update(profileMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toasty.success(getApplicationContext(), "Update successful", Toast.LENGTH_SHORT, true).show();
                            getInfo();
                        }
                    });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}