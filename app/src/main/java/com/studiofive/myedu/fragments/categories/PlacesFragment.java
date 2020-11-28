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

public class PlacesFragment extends Fragment {
    @BindView(R.id.fragment_places_recyclerview)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private PlacesFragment mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.places, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = this;

        ArrayList<SoundGame> list = new ArrayList();
        list.add(new SoundGame(R.drawable.place1, R.raw.straw, "Church" ));
        list.add(new SoundGame(R.drawable.place2, R.raw.straw, "Hotel" ));
        list.add(new SoundGame(R.drawable.place3, R.raw.straw, "Mosque" ));
        list.add(new SoundGame(R.drawable.place4, R.raw.straw, "House" ));
        list.add(new SoundGame(R.drawable.place5, R.raw.gargle, "Restaurant" ));
        list.add(new SoundGame(R.drawable.place6, R.raw.gargle, "Mountain" ));
        list.add(new SoundGame(R.drawable.place7, R.raw.straw, "Prison" ));
        list.add(new SoundGame(R.drawable.place8, R.raw.straw, "Garden" ));
        list.add(new SoundGame(R.drawable.place9, R.raw.straw, "Volcano" ));
        list.add(new SoundGame(R.drawable.place10, R.raw.straw, "Classroom" ));
        list.add(new SoundGame(R.drawable.place11, R.raw.straw, "Beach" ));
        list.add(new SoundGame(R.drawable.place12, R.raw.straw, "Barrack" ));
        list.add(new SoundGame(R.drawable.place13, R.raw.straw, "Library" ));
        list.add(new SoundGame(R.drawable.place14, R.raw.straw, "Desert" ));
        list.add(new SoundGame(R.drawable.place15, R.raw.fry, "Zoo" ));
        list.add(new SoundGame(R.drawable.place16, R.raw.fry, "Train Station" ));
        list.add(new SoundGame(R.drawable.place17, R.raw.fry, "Airport" ));
        list.add(new SoundGame(R.drawable.place18, R.raw.fry, "Waterfall" ));
        list.add(new SoundGame(R.drawable.place19, R.raw.fry, "Hot Spring" ));
        list.add(new SoundGame(R.drawable.place20, R.raw.fry, "Museum" ));

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
