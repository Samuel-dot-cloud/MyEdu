package com.studiofive.myedu.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.studiofive.myedu.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SoundGame_Intro extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    @BindView(R.id.sliderLayout)
    SliderLayout sliderLayout;
    
    private HashMap<String, Integer> sliderImages;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_game__intro);
        ButterKnife.bind(this);
        
        setupSlider();
    }

    private void setupSlider() {
        sliderImages = new HashMap<>();
        sliderImages.put("Doughnuts", R.drawable.food4);
        sliderImages.put("French Fries", R.drawable.food5);
        sliderImages.put("Cakes", R.drawable.food6);
        sliderImages.put("Raspberry smoothie", R.drawable.drink5);
        sliderImages.put("Chocolate milkshake", R.drawable.drink6);
        sliderImages.put("Coca cola", R.drawable.drink7);

        for (String name: sliderImages.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(sliderImages.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }


}