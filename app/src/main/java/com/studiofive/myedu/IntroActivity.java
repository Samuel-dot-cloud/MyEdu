package com.studiofive.myedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //fill list screen
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Preschool inclusive", "Contains well tailored content for children", R.drawable.pic1));
        mList.add(new ScreenItem("High School inclusive", "Contains well tailored content for high school students", R.drawable.pic2));
        mList.add(new ScreenItem("Simply fun", "Through sound games and puzzles, it makes learning simply fun!", R.drawable.pic3));

        //setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);


    }
}