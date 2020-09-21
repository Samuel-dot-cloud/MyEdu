package com.studiofive.myedu.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.Users;
import com.studiofive.myedu.intro.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.inputEmailLogin)
    EditText mEmailLogin;
    @BindView(R.id.inputPasswordLogin)
    EditText mPasswordLogin;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;
    @BindView(R.id.forgotPassword)
    TextView mForgotPassword;
    //    @BindView(R.id.googleLogin)
//    ImageView mGoogleLogin;
//    @BindView(R.id.facebookLogin)
//    ImageView mFacebookLogin;
    @BindView(R.id.goToSignUp)
    TextView mGoToSignUp;

    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private FirebaseFirestore mFirestore;
    private Users users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mProgressDialog = new ProgressDialog(this);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDefault();
            }
        });

        mGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToSignUpActivity();
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToResetPasswordActivity();
            }
        });
    }

    private void LoginDefault() {
        String email = mEmailLogin.getText().toString();
        String password = mPasswordLogin.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toasty.warning(this, "Please enter valid details!!", Toast.LENGTH_SHORT, true).show();
        } else {
            mProgressDialog.setTitle("Logging In");
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setCanceledOnTouchOutside(true);
            mProgressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                                if (firebaseUser!=null){
//                                    String userID = firebaseUser.getUid();
//                                  users = new Users(userID, users.getUserName(), users.getPersonalMantra(), users.getProfileImage(), users.getGender(), users.getEmail());
//                                    mFirestore.collection("Users").document(firebaseUser.getUid()).set(users)
//                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                @Override
//                                                public void onSuccess(Void aVoid) {
//                                                    mProgressDialog.dismiss();
//                                                    Toasty.success(LoginActivity.this, "Logged in Successfully!!", Toast.LENGTH_SHORT, true).show();
//                                                    sendUserToSplashActivity();
//                                                }
//                                            }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//
//                                            String message = task.getException().toString();
//                                            Toasty.error(LoginActivity.this, "Error: " + message, Toast.LENGTH_SHORT, true).show();
//                                        }
//                                    });
//                                    mProgressDialog.dismiss();
//                                }

                                mProgressDialog.dismiss();
                                Toasty.success(LoginActivity.this, "Logged in Successfully!!", Toast.LENGTH_SHORT, true).show();
                                sendUserToSplashActivity();
                            } else {
                                String message = task.getException().toString();
                                Toasty.error(LoginActivity.this, "Error: " + message, Toast.LENGTH_SHORT, true).show();
                            }
                            mProgressDialog.dismiss();
                        }
                    });
        }
    }

    private void sendUserToSplashActivity() {
        Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void sendUserToSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void sendUserToResetPasswordActivity() {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }

}