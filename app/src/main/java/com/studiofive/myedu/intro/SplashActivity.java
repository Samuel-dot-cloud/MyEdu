package com.studiofive.myedu.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.MainActivity;
import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.Users;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIMER = 3000;

    @BindView(R.id.background_image)
    GifImageView mBackgroundImage;
    @BindView(R.id.powered_by_line)
    TextView mPoweredBy;

    Animation sideAnim, bottomAnim;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        mBackgroundImage.setAnimation(sideAnim);
        mPoweredBy.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }

//    private void verifyUserExistence(){
//        String uid = mAuth.getCurrentUser().getUid();
//        mFirestore.collection()
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}