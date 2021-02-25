package com.studiofive.myedu.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.activities.segments.PreQuizActivity;
import com.studiofive.myedu.adapters.CategoriesAdapter;
import com.studiofive.myedu.adapters.FeaturedAdapter;
import com.studiofive.myedu.adapters.MostViewedAdapter;
import com.studiofive.myedu.classes.helper_class.CategoriesHelperClass;
import com.studiofive.myedu.classes.helper_class.FeaturedHelperClass;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.featured_recycler)
    RecyclerView featuredRecycler;
    @BindView(R.id.most_viewed_recyclerview)
    RecyclerView mostViewedRecycler;
    @BindView(R.id.categories_recyclerview)
    RecyclerView categoriesRecycler;
    @BindView(R.id.profile_home)
    RelativeLayout profile;
    @BindView(R.id.funQuiz_home)
    RelativeLayout funQuiz;
    @BindView(R.id.highschool_home)
    RelativeLayout highSchool;
    @BindView(R.id.all_quiz_view)
    TextView quizView;

    private Unbinder unbinder;
    private Context mContext;
    RecyclerView.Adapter adapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        Random r = new Random();
        int[] colors = new int[]{0xFF616261,0xFF131313, 0xFF125FF8, 0xFFFFEA00, 0xFF5D4037};

//        getRandom(r, colors);
        featuredRecyclerMethod();
        mostViewedRecyclerMethod();
        categoriesRecyclerMethod();
        initActionClick();

        return view;
    }

    private void initActionClick() {
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ProfileFragment();
                replaceFragment(fragment);
            }
        });

        highSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new HighSchoolFragment();
                replaceFragment(fragment);
            }
        });

        funQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizActivity();
            }
        });

        quizView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizActivity();
            }
        });
    }

    private void featuredRecyclerMethod() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredTopics = new ArrayList<>();
        featuredTopics.add(new FeaturedHelperClass(R.drawable.calculus, "Calculus", "The wonderful world of calculus", 4));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.design, "Drawing And Design", "The wonderful world of  Design", 4));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.elec2, "Computer Studies", "The wonderful world of computer studies", 3));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.chemistry, "Chemistry", "The wonderful world of chemistry", 5));

        adapter = new FeaturedAdapter(featuredTopics);
        featuredRecycler.setAdapter(adapter);
    }

    private void mostViewedRecyclerMethod() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        ArrayList<FeaturedHelperClass> mostViewedTopics = new ArrayList<>();
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.calculus, "Calculus", "The wonderful world of calculus", 4));
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.design, "Drawing And Design", "The wonderful world of  Design", 4));
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.elec2, "Computer Studies", "The wonderful world of computer studies", 4));
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.chemistry, "Chemistry", "The wonderful world of chemistry", 4));

        adapter = new MostViewedAdapter(mostViewedTopics);
        mostViewedRecycler.setAdapter(adapter);
    }

    private void categoriesRecyclerMethod(){
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        ArrayList<CategoriesHelperClass> categories = new ArrayList<>();
        categories.add(new CategoriesHelperClass(R.drawable.chemistry, "Chemistry"));
        categories.add(new CategoriesHelperClass(R.drawable.calculus, "Calculus"));
        categories.add(new CategoriesHelperClass(R.drawable.design, "Design"));
        categories.add(new CategoriesHelperClass(R.drawable.elec2, "Computer"));

//        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[] {0xFFFFEA00, 0xFF5D4037});

        adapter = new CategoriesAdapter(categories);
        categoriesRecycler.setAdapter(adapter);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void quizActivity(){
        Intent intent = new Intent(this.mContext, PreQuizActivity.class);
        startActivity(intent);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}