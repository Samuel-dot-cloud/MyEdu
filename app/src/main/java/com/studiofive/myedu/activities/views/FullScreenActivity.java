package com.studiofive.myedu.activities.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.common.internal.service.Common;
import com.studiofive.myedu.R;

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

        fullPhoto.setImageBitmap(Common.IM);
    }
}