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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.studiofive.myedu.MainActivity;
import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.ExamCategory;
import com.studiofive.myedu.classes.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.inputName)
    EditText mInputName;
    @BindView(R.id.inputEmail)
    EditText mInputEmail;
    @BindView(R.id.inputPassword)
    EditText mInputPassword;
    @BindView(R.id.btnSignUp)
    Button mBtnSignUp;
    //    @BindView(R.idn in .googleSignUp)
//    ImageView mGoogleLogin;
//    @BindView(R.id.facebookSignUp)
//    ImageView mFacebookLogin;
    @BindView(R.id.goToLogin)
    TextView mGoToLogin;

    public static List<ExamCategory> categoryList = new ArrayList<>();

    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private static FirebaseFirestore mFirestore;
    private Users users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mProgressDialog = new ProgressDialog(this);

        initActionClick();

    }

    private void initActionClick() {
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUserDefault();
            }
        });

        mGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLoginActivity();
            }
        });
    }

    private void createNewUserDefault() {
        String name = mInputName.getText().toString();
        String email = mInputEmail.getText().toString();
        String password = mInputPassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) {
            Toasty.warning(this, "Please enter valid details!!", Toast.LENGTH_SHORT, true).show();
        } else {
            mProgressDialog.setTitle("Creating New Account");
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setCanceledOnTouchOutside(true);
            mProgressDialog.show();
            // Setting up authentication
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                if (firebaseUser != null) {
                                    String userID = firebaseUser.getUid();
                                    users = new Users(userID, name, "Never give up!", "nil", "default", email, 0);
                                    mFirestore.collection("Users").document(firebaseUser.getUid()).set(users)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                   DocumentReference countDoc =  mFirestore.collection("Users").document("Total_users");
                                                    WriteBatch batch = mFirestore.batch();
                                                    batch.update(countDoc, "Count", FieldValue.increment(1));
                                                    batch.commit()
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    mProgressDialog.dismiss();
                                                                    Toasty.success(SignUpActivity.this, "Account created successfully.", Toast.LENGTH_SHORT, true).show();
                                                                    sendUserToMainActivity();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    String message = task.getException().toString();
                                                                    Toasty.error(SignUpActivity.this, "Error: " + message, Toast.LENGTH_SHORT, true).show();
                                                                }
                                                            });
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toasty.error(SignUpActivity.this, "Something went wrong", Toasty.LENGTH_SHORT, true).show();
                                        }
                                    });
                                    mProgressDialog.dismiss();
                                }

                            } else {
                                String message = task.getException().toString();
                                Toasty.error(SignUpActivity.this, "Error: " + message, Toast.LENGTH_SHORT, true).show();
                            }
                            mProgressDialog.dismiss();
                        }
                    });
        }
    }

    private void sendUserToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void sendUserToMainActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
//                        Toasty.error( SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT,true).show();
//                    }
//                });
//    }
}