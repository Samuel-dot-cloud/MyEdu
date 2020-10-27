package com.studiofive.myedu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.OptionsViewPagerAdapter;
import com.studiofive.myedu.classes.helper_class.OptionItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PreschoolFragment extends Fragment {
    @BindView(R.id.learn_options_viewpager)
    ViewPager optionsPager;
    @BindView(R.id.options_selector)
    TabLayout tabIndicator;

    private OptionsViewPagerAdapter optionsViewPagerAdapter;
    private Unbinder unbinder;
    private Context mContext;



    public PreschoolFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_preschool, container, false);
        unbinder = ButterKnife.bind(this, view);

        final List<OptionItem> mList = new ArrayList<>();
        mList.add(new OptionItem("Sound Games", R.drawable.ic_games));
        mList.add(new OptionItem("Fun Quiz", R.drawable.ic_quiz));

        optionsViewPagerAdapter = new OptionsViewPagerAdapter(mContext, mList);
        optionsPager.setAdapter(optionsViewPagerAdapter);

        tabIndicator.setupWithViewPager(optionsPager);

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}