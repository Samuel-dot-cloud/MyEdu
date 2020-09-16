package com.studiofive.myedu.activities.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.Common;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullScreenActivity extends AppCompatActivity {
    @BindView(R.id.fullPhotoImageView)
    PhotoView fullPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        ButterKnife.bind(this);

        fullPhoto.setImageBitmap(Common.IMAGE_BITMAP);
    }
}