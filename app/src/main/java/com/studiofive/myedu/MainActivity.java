package com.studiofive.myedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studiofive.myedu.activities.SettingsActivity;
import com.studiofive.myedu.authentication.LoginActivity;
import com.studiofive.myedu.fragments.HighSchoolFragment;
import com.studiofive.myedu.fragments.HomeFragment;
import com.studiofive.myedu.fragments.PreschoolFragment;
import com.studiofive.myedu.fragments.ProfileFragment;
import com.studiofive.myedu.fragments.SavedCoursesFragment;
import com.studiofive.myedu.intro.IntroActivity;
import com.studiofive.myedu.utils.Functions;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FirebaseUser mFirebaseUser;
    private FirebaseFirestore mFirestore;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();

        //Toolbar
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        HomeFragment homeFragment = new HomeFragment();
        Functions.changeMainFragment(MainActivity.this, homeFragment);


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
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.profile_settings){
            sendUserToSettingsActivity();
        }
         return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            HomeFragment homeFragment = new HomeFragment();
            Functions.changeMainFragment(MainActivity.this, homeFragment);
            actionBar = getSupportActionBar();
            actionBar.setTitle("Home");
        } else if (id == R.id.nav_profile){
            ProfileFragment profileFragment = new ProfileFragment();
            Functions.changeMainFragment(MainActivity.this, profileFragment);
            actionBar = getSupportActionBar();
            actionBar.setTitle("Profile");
        } else if(id == R.id.preschool){
            PreschoolFragment preschoolFragment = new PreschoolFragment();
            Functions.changeMainFragment(MainActivity.this, preschoolFragment);
            actionBar = getSupportActionBar();
            actionBar.setTitle("Preschool");
        }else if(id == R.id.highSchool){
            HighSchoolFragment highSchoolFragment = new HighSchoolFragment();
            Functions.changeMainFragment(MainActivity.this, highSchoolFragment);
            actionBar = getSupportActionBar();
            actionBar.setTitle("High School");
        }else if(id == R.id.nav_saved_course){
            SavedCoursesFragment savedCoursesFragment = new SavedCoursesFragment();
            Functions.changeMainFragment(MainActivity.this, savedCoursesFragment);
            actionBar = getSupportActionBar();
            actionBar.setTitle("Saved Courses");
        }else if(id == R.id.nav_logout){
            Toasty.info(this, "Signing out", Toast.LENGTH_SHORT, true).show();
            FirebaseAuth.getInstance().signOut();
            sendUserToLoginActivity();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseUser == null){
            sendUserToIntroActivity();
        }else{
            getNavInfo();
        }
    }

    private void getNavInfo() {
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.navHeaderTitle);
        TextView navMantra = headerView.findViewById(R.id.navHeaderText);
        CircleImageView navProfilePhoto = headerView.findViewById(R.id.navHeaderImageView);

        mFirestore.collection("Users").document(mFirebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userName = Objects.requireNonNull(documentSnapshot.get("userName")).toString();
                String profileImage = documentSnapshot.getString("profileImage");
                String personalMantra = Objects.requireNonNull(documentSnapshot.get("personalMantra")).toString();
                navUserName.setText(userName);
                navMantra.setText(personalMantra);
                Glide.with(MainActivity.this).load(profileImage).placeholder(R.drawable.logo).into(navProfilePhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(MainActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    private void sendUserToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void sendUserToIntroActivity() {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void sendUserToSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }


}