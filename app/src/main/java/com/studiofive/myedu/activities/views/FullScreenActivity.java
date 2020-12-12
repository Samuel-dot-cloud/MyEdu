package com.studiofive.myedu.activities.views;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.github.chrisbanes.photoview.PhotoView;
import com.studiofive.myedu.R;
import com.studiofive.myedu.activities.ProfileSettingsActivity;
import com.studiofive.myedu.classes.Common;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import iamutkarshtiwari.github.io.ananas.BaseActivity;
import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity;
import iamutkarshtiwari.github.io.ananas.editimage.ImageEditorIntentBuilder;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class FullScreenActivity extends AppCompatActivity {
    @BindView(R.id.fullPhotoImageView)
    PhotoView fullPhotoView;
    @BindView(R.id.btn_share)
    ImageButton shareButton;
    @BindView(R.id.btn_edit)
    ImageButton editButton;

    private Bitmap mBitmap;
    private final int PHOTO_EDITOR_REQUEST_CODE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        ButterKnife.bind(this);

        fullPhotoView.setImageBitmap(Common.IMAGE_BITMAP);

//        Resources r = FullScreenActivity.this.getResources();
//        Bitmap bm = BitmapFactory.decodeResource(r, );
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
//        byte[] b = baos.toByteArray();
//
//        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        initActionClick();
    }

    private void initActionClick() {
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encoded = BitMapToString(Common.IMAGE_BITMAP);
                try {
                    Intent intent = new ImageEditorIntentBuilder(FullScreenActivity.this, "encoded", "gkhh")
                            .withAddText() // Add the features you need
                            .withPaintFeature()
                            .withFilterFeature()
                            .withRotateFeature()
                            .withCropFeature()
                            .withBrightnessFeature()
                            .withSaturationFeature()
                            .withBeautyFeature()
                            .withStickerFeature()
                            .forcePortrait(true)  // Add this to force portrait mode (It's set to false by default)
                            .setSupportActionBarVisibility(false) // To hide app's default action bar
                            .build();

                    EditImageActivity.start(FullScreenActivity.this, intent, PHOTO_EDITOR_REQUEST_CODE);
                } catch (Exception e) {
                    Toasty.error(FullScreenActivity.this, e.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    private void shareImage(){
        Drawable mDrawable = fullPhotoView.getDrawable();
        mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "Profile Image ", "Image description");
        Uri uri = Uri.parse(path);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Image"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_EDITOR_REQUEST_CODE) { // same code you used while starting
            boolean isImageEdit = data.getBooleanExtra(EditImageActivity.IS_IMAGE_EDITED, false);
        }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}