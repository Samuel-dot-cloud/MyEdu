package com.studiofive.myedu.activities.segments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.studiofive.myedu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity {
    @BindView(R.id.score_text)
    TextView scoreText;
    @BindView(R.id.score_done)
    Button scoreDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);

        String score_string = getIntent().getStringExtra("SCORE");
        scoreText.setText(score_string);

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