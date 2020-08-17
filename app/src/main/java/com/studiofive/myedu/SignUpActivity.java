package com.studiofive.myedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.inputEmail)
    EditText mInputEmail;
    @BindView(R.id.inputPassword)
    EditText mInputPassword;
    @BindView(R.id.btnSignUp)
    Button mBtnSignUp;
    @BindView(R.id.googleSignUp)
    ImageView mGoogleLogin;
    @BindView(R.id.facebookSignUp)
    ImageView mFacebookLogin;
    @BindView(R.id.goToLogin)
    TextView mGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        mGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLoginActivity();
            }
        });
    }


    private void sendUserToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}