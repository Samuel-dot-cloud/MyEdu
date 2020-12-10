package com.studiofive.myedu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.studiofive.myedu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HighSchoolFragment extends Fragment {
    @BindView(R.id.category_grid_high_school)
    GridView categoryView;
    private Unbinder unbinder;



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
         return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}