package com.studiofive.myedu.activities.segments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import com.studiofive.myedu.R;
import com.studiofive.myedu.fragments.categories.AnimalsFragment;
import com.studiofive.myedu.fragments.categories.ClothingFragment;
import com.studiofive.myedu.fragments.categories.DrinksFragment;
import com.studiofive.myedu.fragments.categories.ElectronicsFragment;
import com.studiofive.myedu.fragments.categories.FoodsFragment;
import com.studiofive.myedu.fragments.categories.FruitsFragment;
import com.studiofive.myedu.fragments.categories.PlacesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SoundCategoryActivity extends AppCompatActivity {
    @BindView(R.id.sound_category_toolbar)
    Toolbar toolbar;

    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_category);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        action = getIntent().getStringExtra("category");

        if (findViewById(R.id.sound_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            navigationContext();
        }
    }

    private void navigationContext() {
        if (action.compareTo("drinks") == 0) {
            DrinksFragment drinksFragment = new DrinksFragment();
            drinksFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, drinksFragment).commit();

            getSupportActionBar().setTitle("Drinks");
        } else if (action.compareTo("foods") == 0) {
            FoodsFragment foodsFragment = new FoodsFragment();
            foodsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, foodsFragment).commit();

            getSupportActionBar().setTitle("Foods");
        } else if (action.compareTo("fruits") == 0) {
            FruitsFragment fruitsFragment = new FruitsFragment();
            fruitsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, fruitsFragment).commit();

            getSupportActionBar().setTitle("Fruits");
        } else if (action.compareTo("animals") == 0) {
            AnimalsFragment animalsFragment = new AnimalsFragment();
            animalsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, animalsFragment).commit();

            getSupportActionBar().setTitle("Animals");
        } else if (action.compareTo("places") == 0) {
            PlacesFragment placesFragment = new PlacesFragment();
            placesFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, placesFragment).commit();

            getSupportActionBar().setTitle("Places");
        } else if (action.compareTo("electronics") == 0) {
            ElectronicsFragment electronicsFragment = new ElectronicsFragment();
            electronicsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, electronicsFragment).commit();

            getSupportActionBar().setTitle("Electronics");
        } else if (action.compareTo("clothing") == 0){
            ClothingFragment clothingFragment = new ClothingFragment();
            clothingFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_fragment_container, clothingFragment).commit();

            getSupportActionBar().setTitle("Clothing");
        }else {
            Toasty.error(SoundCategoryActivity.this, "Something went wrong", Toast.LENGTH_SHORT, true).show();
            Intent intent = new Intent(SoundCategoryActivity.this, SoundGameActivity.class);
            startActivity(intent);
        }
    }
}