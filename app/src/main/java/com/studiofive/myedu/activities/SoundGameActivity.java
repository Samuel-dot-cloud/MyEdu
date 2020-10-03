package com.studiofive.myedu.activities;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.animations.DescriptionAnimation;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.TextSliderView;
import com.glide.slider.library.tricks.ViewPagerEx;
import com.studiofive.myedu.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SoundGameActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    @BindView(R.id.sliderLayout)
    SliderLayout sliderLayout;

    public static Integer goBackCounter = 0;
    public Context mContext;
    private MediaPlayer musicFile;
    private AudioManager audio;
    private Activity mActivity;
    private static final String TAG = "MainActivity";
    private HashMap<String, Integer> sliderImages;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Activates context
        mContext = SoundGameActivity.this;
        mActivity = SoundGameActivity.this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showMainScreen();
        ButterKnife.bind(this);

        setupSlider();
    }

    private void setupSlider() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();

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
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }

    //Show various pages
    private void showMainScreen(){
        setContentView(R.layout.activity_sound_game);
    }

    private void showDrinksPage(){
        setContentView(R.layout.drinks);
        goBackCounter = 0;
    }

    private void showFoodsPage(){
        setContentView(R.layout.foods);
        goBackCounter = 0;
    }

    private void showAnimalsPage(){
        setContentView(R.layout.animals);
        goBackCounter = 0;
    }
    private void showPlacesPage(){
        setContentView(R.layout.places);
        goBackCounter = 0;
    }
    private void showCommonsPage(){
        setContentView(R.layout.objects);
        goBackCounter = 0;
    }
    private void showElectronicsPage(){
        setContentView(R.layout.fruits);
        goBackCounter = 0;
    }
    private void showClothingPage(){
        setContentView(R.layout.clothes);
        goBackCounter = 0;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toasty.info(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT, true).show();
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

    @Override
    public void onBackPressed() {
        if (goBackCounter == 2) {
            if (!((Activity) mContext).isFinishing()) {
                Toasty.success(mContext,  getResources().getString(R.string.exit), Toast.LENGTH_SHORT, true).show();
            }
        }
    }


    public void clickButton(View view) {
    }
}