package com.studiofive.myedu.fragments.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.SoundGameAdapter;
import com.studiofive.myedu.classes.SoundGame;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodsFragment extends Fragment {
    @BindView(R.id.fragment_food_recyclerview)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private FoodsFragment mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foods, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = this;

        ArrayList<SoundGame> list = new ArrayList();
        list.add(new SoundGame(R.drawable.food1, R.raw.straw, "Chocolate" ));
        list.add(new SoundGame(R.drawable.food2, R.raw.straw, "Meringue" ));
        list.add(new SoundGame(R.drawable.food3, R.raw.straw, "Succotash" ));
        list.add(new SoundGame(R.drawable.food4, R.raw.straw, "Pie" ));
        list.add(new SoundGame(R.drawable.food5, R.raw.gargle, "Pizza" ));
        list.add(new SoundGame(R.drawable.food6, R.raw.gargle, "Cake" ));
        list.add(new SoundGame(R.drawable.food7, R.raw.straw, "Ugali" ));
        list.add(new SoundGame(R.drawable.food8, R.raw.straw, "Steak" ));
        list.add(new SoundGame(R.drawable.food9, R.raw.straw, "" ));
        list.add(new SoundGame(R.drawable.food10, R.raw.straw, "Beef stew" ));
        list.add(new SoundGame(R.drawable.food11, R.raw.straw, "Jambalaya" ));
        list.add(new SoundGame(R.drawable.food12, R.raw.straw, "Rice" ));
        list.add(new SoundGame(R.drawable.food13, R.raw.straw, "Chicken" ));
        list.add(new SoundGame(R.drawable.food14, R.raw.straw, "Chips" ));
        list.add(new SoundGame(R.drawable.food15, R.raw.fry, "Hot dog" ));
        list.add(new SoundGame(R.drawable.food16, R.raw.fry, "Hamburger" ));
        list.add(new SoundGame(R.drawable.food17, R.raw.fry, "Doughnut" ));
        list.add(new SoundGame(R.drawable.food18, R.raw.fry, "Waterfall" ));
        list.add(new SoundGame(R.drawable.food19, R.raw.fry, "Samosa" ));
        list.add(new SoundGame(R.drawable.food20, R.raw.fry, "" ));

        SoundGameAdapter adapter = new SoundGameAdapter(list, mContext.getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext.getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
