package com.studiofive.myedu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.FeaturedAdapter;
import com.studiofive.myedu.classes.helper_class.FeaturedHelperClass;

import java.util.ArrayList;

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

        featuredRecyclerMethod();

        return view;
    }

    private void featuredRecyclerMethod() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredTopics = new ArrayList<>();
        featuredTopics.add(new FeaturedHelperClass(R.drawable.calculus, "Calculus", "The wonderful world of calculus", 4));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.design, "Drawing And Design", "The wonderful world of  Design", 4));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.computer, "Computer Studies", "The wonderful world of computer studies", 3));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.chemistry, "Chemistry", "The wonderful world of chemistry", 5));

        adapter = new FeaturedAdapter(featuredTopics);
        featuredRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}