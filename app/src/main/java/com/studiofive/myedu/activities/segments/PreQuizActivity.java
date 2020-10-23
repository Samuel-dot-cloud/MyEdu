package com.studiofive.myedu.activities.segments;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.CategoryGridAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreQuizActivity extends AppCompatActivity {
    @BindView(R.id.category_grid_view)
    GridView quizCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_quiz);
        ButterKnife.bind(this);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("Animals");
        categoryList.add("Drinks");
        categoryList.add("Foods");
        categoryList.add("Places");
        categoryList.add("Electronics");
        categoryList.add("Clothing");

        CategoryGridAdapter categoryGridAdapter = new CategoryGridAdapter(categoryList);
        quizCategory.setAdapter(categoryGridAdapter);
    }
}