package com.studiofive.myedu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.ExamCategoryAdapter;
import com.studiofive.myedu.classes.ExamCategory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HighSchoolFragment extends Fragment {
    @BindView(R.id.category_grid_high_school)
    GridView categoryView;
    private Unbinder unbinder;
    public static List<ExamCategory> categoryList = new ArrayList<>();



    public HighSchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_high_school, container, false);
        unbinder = ButterKnife.bind(this, view);
        loadCategories();

        ExamCategoryAdapter adapter = new ExamCategoryAdapter(categoryList);
        categoryView.setAdapter(adapter);
         return view;
    }

    private void loadCategories() {
        categoryList.clear();
        categoryList.add(new ExamCategory("1", "Calculus", 20));
        categoryList.add(new ExamCategory("2", "Geometry", 30));
        categoryList.add(new ExamCategory("3", "Algebra", 5));
        categoryList.add(new ExamCategory("4", "Chemistry", 10));
        categoryList.add(new ExamCategory("5", "Physics", 7));
        categoryList.add(new ExamCategory("6", "Engineering Drawing", 9));
        categoryList.add(new ExamCategory("7", "Development Studies", 15));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}