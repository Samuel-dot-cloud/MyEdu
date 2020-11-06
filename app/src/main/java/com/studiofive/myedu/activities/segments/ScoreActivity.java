package com.studiofive.myedu.activities.segments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.studiofive.myedu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity {
    @BindView(R.id.results_correct_text)
    TextView resultCorrect;
    @BindView(R.id.results_wrong_text)
    TextView resultWrong;
    @BindView(R.id.results_missed_text)
    TextView resultMissed;
    @BindView(R.id.results_percent)
    TextView resultPercent;
    @BindView(R.id.results_progress)
    ProgressBar resultProgress;
    @BindView(R.id.score_done)
    Button scoreDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);

        Long correct = getIntent().getLongExtra("correct", 0);
        Long wrong = getIntent().getLongExtra("wrong", 0);
        Long missed = getIntent().getLongExtra("missed", 0);
        resultCorrect.setText(correct.toString());
        resultWrong.setText(wrong.toString());
        resultMissed.setText(missed.toString());

        //Calculate Progress
        Long total = correct + wrong + missed;
        Long percent = (correct * 100)/total;
        resultPercent.setText(percent + "%");
        resultProgress.setProgress(percent.intValue());


        scoreDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, PreQuizActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}