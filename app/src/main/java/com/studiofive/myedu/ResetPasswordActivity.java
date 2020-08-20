package com.studiofive.myedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ResetPasswordActivity extends AppCompatActivity {
    @BindView(R.id.reset_password_button)
    Button mResetPasswordButton;
    @BindView(R.id.resetPasswordEmailField)
    EditText mEmailField;
    @BindView(R.id.arrow_back_reset)
    ImageView mBackReset;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mResetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        mBackReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLoginActivity();
            }
        });
    }

    private void resetPassword(){
        String userEmail = mEmailField.getText().toString();
        if (TextUtils.isEmpty(userEmail)){
            Toasty.warning(ResetPasswordActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT, true).show();
        }else {
            mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toasty.info(ResetPasswordActivity.this, "Please check your mail", Toast.LENGTH_SHORT, true).show();
                        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                    } else {
                        String message = task.getException().getMessage();
                        Toasty.error(ResetPasswordActivity.this, "Error occurred: " + message, Toast.LENGTH_SHORT, true).show();
                    }
                }
            });
        }
    }

    private void sendUserToLoginActivity() {
        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}