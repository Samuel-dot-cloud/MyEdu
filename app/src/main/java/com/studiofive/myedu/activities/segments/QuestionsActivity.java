package com.studiofive.myedu.activities.segments;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static com.studiofive.myedu.activities.segments.SetsActivity.setsIDs;
import static com.studiofive.myedu.intro.SplashActivity.categoryList;
import static com.studiofive.myedu.intro.SplashActivity.selected_category_index;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.quiz_questions_number)
    TextView questionCount;
    @BindView(R.id.quiz_questions_view)
    TextView question;
    @BindView(R.id.countdown)
    TextView countdownTimer;
    @BindView(R.id.option1)
    Button option1;
    @BindView(R.id.option2)
    Button option2;
    @BindView(R.id.option3)
    Button option3;
    @BindView(R.id.option4)
    Button option4;

    private CountDownTimer countDownTimer;

    private List<Question> questionsList;
    private int questionNum = 0;
    private int correctAns;
    private int wrongAns;
    private int notAnswered;
    private FirebaseFirestore mFirestore;
    private int setNum;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);

        setNum = getIntent().getIntExtra("SetNo", 1);

        loadingDialog = new Dialog(QuestionsActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        questionsList = new ArrayList<>();

        mFirestore = FirebaseFirestore.getInstance();

        initActionClick();
        getQuestionsList();

        correctAns = 0;
        wrongAns = 0;
    }

    private void initActionClick() {
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
    }

    private void getQuestionsList() {
        questionsList.clear();

        mFirestore.collection("PreQuiz").document(categoryList.get(selected_category_index).getId())
                .collection(setsIDs.get(setNum)).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            docList.put(doc.getId(), doc);
                        }

                        QueryDocumentSnapshot questionListDoc = docList.get("Questions_List");

                        String count = questionListDoc.getString("Count");

                        for (int i = 0; i < Integer.valueOf(count); i++) {
                            String questionID = questionListDoc.getString("Question" + String.valueOf(i + 1) + "_ID");

                            QueryDocumentSnapshot quesDoc = docList.get(questionID);

                            questionsList.add(new Question(
                                    quesDoc.getString("Question"),
                                    quesDoc.getString("A"),
                                    quesDoc.getString("B"),
                                    quesDoc.getString("C"),
                                    quesDoc.getString("D"),
                                    Integer.valueOf(quesDoc.getString("Answer"))
                            ));

                        }
                        setQuestion();

                        loadingDialog.dismiss();

                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loadingDialog.dismiss();
                        Toasty.error(QuestionsActivity.this, e.getMessage(), Toast.LENGTH_SHORT, true).show();
                    }
                });
    }

    private void setQuestion() {
        countdownTimer.setText(String.valueOf(10));

        question.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOptionA());
        option2.setText(questionsList.get(0).getOptionB());
        option3.setText(questionsList.get(0).getOptionC());
        option4.setText(questionsList.get(0).getOptionD());

        questionCount.setText(String.valueOf(1) + "/" + String.valueOf(questionsList.size()));

        startTimer();
        questionNum = 0;
    }

    private void startTimer() {
         countDownTimer = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished< 10000)
                countdownTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };
        countDownTimer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        int selectedOption = 0;

        switch (v.getId()){
            case R.id.option1:
                selectedOption = 1;
                break;

            case R.id.option2:
                selectedOption = 2;
                break;

            case R.id.option3:
                selectedOption = 3;
                break;

            case R.id.option4:
                selectedOption = 4;
                break;
            default:
        }

        countDownTimer.cancel();
        checkAnswer(selectedOption, v);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(int selectedOption, View view) {
        if (selectedOption == questionsList.get(questionNum).getCorrectAns()){
            //Right answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            correctAns++;
        }else{
            //Wrong answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            wrongAns++;

            switch (questionsList.get(questionNum).getCorrectAns()){
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

                case 3:
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

                case 4:
                    option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, 2000);
    }

    private void changeQuestion() {
        if (questionNum<questionsList.size() -1){

            questionNum++;

            playAnim(question,0,0);
            playAnim(option1,0,1);
            playAnim(option2,0,2);
            playAnim(option3,0,3);
            playAnim(option4,0,4);

            questionCount.setText(String.valueOf(questionNum + 1)+ "/"+ String.valueOf(questionsList.size()));
            countdownTimer.setText(String.valueOf(10));
            startTimer();


        }else{
            // Go to score activity
            Intent intent = new Intent(QuestionsActivity.this, ScoreActivity.class);
            intent.putExtra("correct", Long.valueOf(correctAns));
            intent.putExtra("wrong", Long.valueOf(wrongAns));
            intent.putExtra("missed", Long.valueOf(notAnswered));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            QuestionsActivity.this.finish();
        }
    }

    private void playAnim(View view, final int value, int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value == 0){
                            switch (viewNum){
                                case 0:
                                    ((TextView) view).setText(questionsList.get(questionNum).getQuestion());
                                    break;

                                case 1:
                                    ((Button)view).setText(questionsList.get(questionNum).getOptionA());
                                    break;

                                case 2:
                                    ((Button)view).setText(questionsList.get(questionNum).getOptionB());
                                    break;

                                case 3:
                                    ((Button)view).setText(questionsList.get(questionNum).getOptionC());
                                    break;

                                case 4:
                                    ((Button)view).setText(questionsList.get(questionNum).getOptionD());
                                    break;
                            }

                            if (viewNum != 0){
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E99c03")));
                            }

                            playAnim(view, 1, viewNum);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }


}