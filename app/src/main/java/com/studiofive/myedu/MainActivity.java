package com.studiofive.myedu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.studiofive.myedu.fragments.HighSchoolFragment;
import com.studiofive.myedu.fragments.HomeFragment;
import com.studiofive.myedu.fragments.PreschoolFragment;
import com.studiofive.myedu.fragments.ProfileFragment;
import com.studiofive.myedu.fragments.SavedCoursesFragment;
import com.studiofive.myedu.utils.Functions;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Toolbar
        setSupportActionBar(toolbar);
        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            HomeFragment homeFragment = new HomeFragment();
            Functions.changeMainFragment(MainActivity.this, homeFragment);
        } else if (id == R.id.nav_profile){
            ProfileFragment profileFragment = new ProfileFragment();
            Functions.changeMainFragment(MainActivity.this, profileFragment);
        } else if(id == R.id.preschool){
            PreschoolFragment preschoolFragment = new PreschoolFragment();
            Functions.changeMainFragment(MainActivity.this, preschoolFragment);
        }else if(id == R.id.highSchool){
            HighSchoolFragment highSchoolFragment = new HighSchoolFragment();
            Functions.changeMainFragment(MainActivity.this, highSchoolFragment);
        }else if(id == R.id.nav_saved_course){
            SavedCoursesFragment savedCoursesFragment = new SavedCoursesFragment();
            Functions.changeMainFragment(MainActivity.this, savedCoursesFragment);
        }else if(id == R.id.nav_logout){
            Toasty.info(this, "Signing out action", Toast.LENGTH_SHORT, true).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        HomeFragment homeFragment = new HomeFragment();
//        Functions.changeMainFragment(MainActivity.this, homeFragment);
//    }
}