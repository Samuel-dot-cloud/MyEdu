package com.studiofive.myedu.activities.segments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import com.studiofive.myedu.R;
import com.studiofive.myedu.fragments.categories.DrinksFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SoundCategoryActivity extends FragmentActivity {
    @BindView(R.id.sound_category_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_category);
        ButterKnife.bind(this);

        String drinks = getIntent().getStringExtra("drinks");
        String foods = getIntent().getStringExtra("drinks");
        String fruits = getIntent().getStringExtra("fruits");
        String animals = getIntent().getStringExtra("animals");
        String places = getIntent().getStringExtra("places");
        String electronics = getIntent().getStringExtra("elec");
        String clothing = getIntent().getStringExtra("clothing");

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.sound_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            DrinksFragment firstFragment = new DrinksFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, firstFragment).commit();
        }
    }
}