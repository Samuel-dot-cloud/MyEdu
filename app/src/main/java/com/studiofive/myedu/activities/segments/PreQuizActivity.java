package com.studiofive.myedu.activities.segments;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.CategoryGridAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.studiofive.myedu.intro.SplashActivity.categoryList;

public class PreQuizActivity extends AppCompatActivity {
    @BindView(R.id.categoryGridView)
    GridView quizCategories;
    @BindView(R.id.quiz_toolbar)
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_quiz);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("Fun Quiz");


        CategoryGridAdapter categoryGridAdapter = new CategoryGridAdapter(categoryList);
        quizCategories.setAdapter(categoryGridAdapter);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}