package com.studiofive.myedu.activities.segments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.CategoryGridAdapter;
import com.studiofive.myedu.classes.Quiz;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreQuizActivity extends AppCompatActivity {
    @BindView(R.id.quiz_categories)
    RecyclerView quizCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_quiz);
        ButterKnife.bind(this);

        List<Quiz> categoryList = new ArrayList<>();
        categoryList.add(new Quiz(R.drawable.elec1, "Electronics"));
        categoryList.add(new Quiz(R.drawable.fruit1, "Fruits"));
        categoryList.add(new Quiz(R.drawable.drink1, "Drinks"));
        categoryList.add(new Quiz(R.drawable.food1, "Foods"));
        categoryList.add(new Quiz(R.drawable.animal1, "Animals"));
        categoryList.add(new Quiz(R.drawable.place1, "Places"));
        categoryList.add(new Quiz(R.drawable.clothe2, "Clothing"));

        CategoryGridAdapter categoryGridAdapter = new CategoryGridAdapter(this, categoryList);
        quizCategories.setLayoutManager(new GridLayoutManager(this, 2));
        quizCategories.setAdapter(categoryGridAdapter);



    }
}