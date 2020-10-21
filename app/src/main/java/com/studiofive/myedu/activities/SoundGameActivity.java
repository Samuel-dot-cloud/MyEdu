package com.studiofive.myedu.activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
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
import com.studiofive.myedu.fragments.categories.AnimalsFragment;
import com.studiofive.myedu.fragments.categories.ClothingFragment;
import com.studiofive.myedu.fragments.categories.DrinksFragment;
import com.studiofive.myedu.fragments.categories.ElectronicsFragment;
import com.studiofive.myedu.fragments.categories.FoodsFragment;
import com.studiofive.myedu.fragments.categories.FruitsFragment;
import com.studiofive.myedu.fragments.categories.PlacesFragment;
import com.studiofive.myedu.utils.Functions;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SoundGameActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    @BindView(R.id.sliderLayout)
    SliderLayout sliderLayout;

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
        setContentView(R.layout.activity_sound_game);
        ButterKnife.bind(this);

        //Initializes audio sound volume
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        setupSlider();
    }

    private void setupSlider() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();

        sliderImages = new HashMap<>();
        sliderImages.put("Cocktail", R.drawable.drink1);
        sliderImages.put("Chocolate", R.drawable.food1);
        sliderImages.put("Mango", R.drawable.fruit1);
        sliderImages.put("Cat", R.drawable.animal1);
        sliderImages.put("Church", R.drawable.place1);
        sliderImages.put("Smart phone", R.drawable.elec1);
        sliderImages.put("Gloves", R.drawable.clothe1);

        for (String name : sliderImages.keySet()) {
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

    public void clickButton(View view) {
        releaseSound();

        //Page Layouts
        if (view.getId() == R.id.drinksBtn) {
            DrinksFragment drinksFragment = new DrinksFragment();
            Functions.changeMainFragment(SoundGameActivity.this, drinksFragment);
        } else if (view.getId() == R.id.foodsBtn) {
            FoodsFragment foodsFragment = new FoodsFragment();
            Functions.changeMainFragment(SoundGameActivity.this, foodsFragment);
        } else if (view.getId() == R.id.fruitsBtn) {
            FruitsFragment fruitsFragment = new FruitsFragment();
            Functions.changeMainFragment(SoundGameActivity.this, fruitsFragment);
        } else if (view.getId() == R.id.animalsBtn) {
            AnimalsFragment animalsFragment = new AnimalsFragment();
            Functions.changeMainFragment(SoundGameActivity.this, animalsFragment);
        } else if (view.getId() == R.id.placesBtn) {
            PlacesFragment placesFragment = new PlacesFragment();
            Functions.changeMainFragment(SoundGameActivity.this, placesFragment);
        } else if (view.getId() == R.id.commonBtn) {
            Toasty.info(SoundGameActivity.this, "Coming soon", Toast.LENGTH_SHORT, true).show();
        } else if (view.getId() == R.id.electronicsBtn) {
            ElectronicsFragment electronicsFragment = new ElectronicsFragment();
            Functions.changeMainFragment(SoundGameActivity.this, electronicsFragment);
        } else if (view.getId() == R.id.clothingBtn) {
            ClothingFragment clothingFragment = new ClothingFragment();
            Functions.changeMainFragment(SoundGameActivity.this, clothingFragment);
        }
    }

    /*
     Play sounds from assets folder
     looping = 1 loop sound / 0 = do not loop sound
    */
    @SuppressWarnings({"SameParameterValue", "deprecation"})
    private void playSound(String fileName, int looping) {
        if ((fileName != null) && (!fileName.equals("-1"))) {
            AssetFileDescriptor afd = null;
            try {
                afd = getAssets().openFd(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if ((afd != null) && (afd.getLength() > 0) && (afd.getStartOffset() > 0)) {
                releaseSound();
                musicFile = new MediaPlayer();
                try {
                    long start;
                    long end;
                    start = afd.getStartOffset();
                    end = afd.getLength();
                    String fileCheckMP;
                    fileCheckMP = afd.getFileDescriptor().toString();
                    if (fileCheckMP != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                                    .setUsage(AudioAttributes.USAGE_GAME)
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .build();
                            musicFile.setAudioAttributes(audioAttributes);
                        } else {
                            musicFile.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        }
                        if (musicFile != null) {
                            musicFile.reset();
                        }
                        musicFile.setDataSource(afd.getFileDescriptor(), start, end);
                        afd.close();
                        try {
                            if (musicFile != null) {
                                musicFile.prepare();
                                if (looping == 1) {
                                    musicFile.setLooping(true);
                                } else {
                                    musicFile.setLooping(false);
                                }
                                if (musicFile.getDuration() > 0) {
                                    musicFile.start();
                                    musicFile.setVolume(3, 3);
                                }
                            }
                        } catch (IllegalStateException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalArgumentException | IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void releaseSound() {
        if (musicFile != null) {
            musicFile.release();
            musicFile = null;
        }
    }

    //When clicking on the back key in the phone/tablet
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
    protected void onDestroy() {
        super.onDestroy();
        releaseSound();
    }

}