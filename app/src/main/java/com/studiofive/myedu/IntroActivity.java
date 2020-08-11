package com.studiofive.myedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make the activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

<<<<<<< HEAD
        //Hide the action bar
        getSupportActionBar().hide();
=======
        //initiate views
        tabIndicator = findViewById(R.id.tab_indicator);
>>>>>>> master

        //fill list screen
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Preschool inclusive", "Contains content well tailored for children", R.drawable.pic1));
        mList.add(new ScreenItem("High School inclusive", "Contains content well tailored for high school students", R.drawable.pic2));
        mList.add(new ScreenItem("Simply fun", "Through sound games and puzzles, it makes learning simply fun!", R.drawable.pic3));

        //setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);


    }
}