package com.studiofive.myedu.activities.segments;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.CategoryGridAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.studiofive.myedu.intro.SplashActivity.categoryList;

public class PreQuizActivity extends AppCompatActivity {
    @BindView(R.id.categoryGridView)
    GridView quizCategories;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_quiz);
        ButterKnife.bind(this);



        CategoryGridAdapter categoryGridAdapter = new CategoryGridAdapter(categoryList);
        quizCategories.setAdapter(categoryGridAdapter);



    }



}