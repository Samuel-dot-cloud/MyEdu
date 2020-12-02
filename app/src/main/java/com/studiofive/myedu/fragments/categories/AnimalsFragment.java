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

public class AnimalsFragment extends Fragment {
    @BindView(R.id.fragment_animal_recyclerview)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private AnimalsFragment mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animals, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = this;

        ArrayList<SoundGame> list = new ArrayList();
        list.add(new SoundGame(R.drawable.animal1, R.raw.straw, "Cat" ));
        list.add(new SoundGame(R.drawable.animal2, R.raw.straw, "Dog" ));
        list.add(new SoundGame(R.drawable.animal3, R.raw.straw, "Rat" ));
        list.add(new SoundGame(R.drawable.animal4, R.raw.straw, "Horse" ));
        list.add(new SoundGame(R.drawable.animal5, R.raw.gargle, "Owl" ));
        list.add(new SoundGame(R.drawable.animal6, R.raw.gargle, "Lion" ));
        list.add(new SoundGame(R.drawable.animal7, R.raw.straw, "Cheetah" ));
        list.add(new SoundGame(R.drawable.animal8, R.raw.straw, "Baboon" ));
        list.add(new SoundGame(R.drawable.animal9, R.raw.straw, "Fish" ));
        list.add(new SoundGame(R.drawable.animal10, R.raw.straw, "Cow" ));
        list.add(new SoundGame(R.drawable.animal11, R.raw.straw, "Pig" ));
        list.add(new SoundGame(R.drawable.animal12, R.raw.straw, "Camel" ));
        list.add(new SoundGame(R.drawable.animal13, R.raw.straw, "Llama" ));
        list.add(new SoundGame(R.drawable.animal14, R.raw.straw, "Sea Horse" ));
        list.add(new SoundGame(R.drawable.animal15, R.raw.fry, "Snake" ));
        list.add(new SoundGame(R.drawable.animal16, R.raw.fry, "Zebra" ));
        list.add(new SoundGame(R.drawable.animal17, R.raw.fry, "" ));
        list.add(new SoundGame(R.drawable.animal18, R.raw.fry, "Lemur" ));
        list.add(new SoundGame(R.drawable.animal19, R.raw.fry, "Koala" ));
        list.add(new SoundGame(R.drawable.animal20, R.raw.fry, "Wildebeest"));

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
