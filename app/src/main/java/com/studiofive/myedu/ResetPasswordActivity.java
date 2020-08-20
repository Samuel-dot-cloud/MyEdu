package com.studiofive.myedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends AppCompatActivity {
    @BindView(R.id.reset_password_button)
    Button mResetPasswordButton;
    @BindView(R.id.reset_password_editText)
    TextInputEditText mResetEditText;
    @BindView(R.id.arrow_back_reset)
    ImageView mBackReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
    }
}