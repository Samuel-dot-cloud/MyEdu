package com.studiofive.myedu.fragments.categories;

import android.content.Context;
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

public class DrinksFragment extends Fragment {
    @BindView(R.id.fragment_drink_recyclerview)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drinks, container, false);
        unbinder = ButterKnife.bind(this, view);
        ArrayList<SoundGame> list = new ArrayList();
        list.add(new SoundGame(R.drawable.drink4, R.raw.straw, "Test drink 1" ));
        list.add(new SoundGame(R.drawable.drink5, R.raw.gargle, "Test drink 2" ));
        list.add(new SoundGame(R.drawable.drink6, R.raw.gargle, "Test drink 3" ));
        list.add(new SoundGame(R.drawable.drink7, R.raw.straw, "Test drink 4" ));

        SoundGameAdapter adapter = new SoundGameAdapter(list, mContext.getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext.getApplicationContext(), 2);

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
