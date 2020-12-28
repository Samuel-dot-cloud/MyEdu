package com.studiofive.myedu.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.ExamCategory;
import com.studiofive.myedu.intro.SplashActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private static FirebaseFirestore mFirestore;
    public static List<ExamCategory> categoryList = new ArrayList<>();


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


        initActionClick();

    }

    private void initActionClick() {
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
//            loadCategories();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
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

//    private void loadCategories(){
//        categoryList.clear();
//        mFirestore.collection("Exams").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, QueryDocumentSnapshot> documentList = new ArrayMap<>();
//                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
//                            documentList.put(doc.getId(), doc);
//                        }
//
//                        QueryDocumentSnapshot categoryListDoc = documentList.get("Categories");
//                        long catCount = categoryListDoc.getLong("Count");
//
//                        for (int i = 1; i <= catCount; i++){
//                            String catID = categoryListDoc.getString("Cat" + String.valueOf(i) + "_ID");
//                            QueryDocumentSnapshot catDoc = documentList.get(catID);
//                            int noOfTests = catDoc.getLong("No_Of_Tests").intValue();
//                            String catName = catDoc.getString("Name");
//                            categoryList.add(new ExamCategory(catID, catName, noOfTests));
//
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toasty.error( LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT,true).show();
//                    }
//                });
//    }
}

