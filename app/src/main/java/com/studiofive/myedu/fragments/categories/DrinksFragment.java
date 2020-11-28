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
    private DrinksFragment mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drinks, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = this;
        ArrayList<SoundGame> list = new ArrayList();
        list.add(new SoundGame(R.drawable.drink1, R.raw.straw, "Test drink 1" ));
        list.add(new SoundGame(R.drawable.drink2, R.raw.straw, "Test drink 2" ));
        list.add(new SoundGame(R.drawable.drink3, R.raw.straw, "Test drink 3" ));
        list.add(new SoundGame(R.drawable.drink4, R.raw.straw, "Test drink 4" ));
        list.add(new SoundGame(R.drawable.drink5, R.raw.gargle, "Test drink 5" ));
        list.add(new SoundGame(R.drawable.drink6, R.raw.gargle, "Test drink 6" ));
        list.add(new SoundGame(R.drawable.drink7, R.raw.straw, "Test drink 7" ));
        list.add(new SoundGame(R.drawable.drink8, R.raw.straw, "Test drink 8" ));
        list.add(new SoundGame(R.drawable.drink9, R.raw.straw, "Test drink 9" ));
        list.add(new SoundGame(R.drawable.drink10, R.raw.straw, "Test drink 10" ));
        list.add(new SoundGame(R.drawable.drink11, R.raw.straw, "Test drink 11" ));
        list.add(new SoundGame(R.drawable.drink12, R.raw.straw, "Test drink 12" ));
        list.add(new SoundGame(R.drawable.drink13, R.raw.straw, "Test drink 13" ));
        list.add(new SoundGame(R.drawable.drink14, R.raw.straw, "Test drink 14" ));
        list.add(new SoundGame(R.drawable.drink15, R.raw.fry, "Test drink 15" ));

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
