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

public class ElectronicsFragment extends Fragment {
    @BindView(R.id.fragment_electronics_recyclerview)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ElectronicsFragment mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.electronics, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = this;

        ArrayList<SoundGame> list = new ArrayList();
        list.add(new SoundGame(R.drawable.elec1, R.raw.straw, "Smartphone" ));
        list.add(new SoundGame(R.drawable.elec2, R.raw.straw, "Laptop" ));
        list.add(new SoundGame(R.drawable.elec3, R.raw.straw, "Tablet" ));
        list.add(new SoundGame(R.drawable.elec4, R.raw.straw, "Camera" ));
        list.add(new SoundGame(R.drawable.elec5, R.raw.gargle, "Mouse" ));
        list.add(new SoundGame(R.drawable.elec6, R.raw.gargle, "Keyboard" ));
        list.add(new SoundGame(R.drawable.elec7, R.raw.straw, "Television" ));
        list.add(new SoundGame(R.drawable.elec8, R.raw.straw, "Fridge" ));
        list.add(new SoundGame(R.drawable.elec9, R.raw.straw, "Blender" ));
        list.add(new SoundGame(R.drawable.elec10, R.raw.straw, "Oven" ));
        list.add(new SoundGame(R.drawable.elec11, R.raw.straw, "Gaming console" ));
        list.add(new SoundGame(R.drawable.elec12, R.raw.straw, "Water pump" ));
        list.add(new SoundGame(R.drawable.elec13, R.raw.straw, "Digital clock" ));
        list.add(new SoundGame(R.drawable.elec14, R.raw.straw, "Smart Watch" ));
        list.add(new SoundGame(R.drawable.elec15, R.raw.fry, "Headphone" ));
        list.add(new SoundGame(R.drawable.elec16, R.raw.fry, "Flash drive" ));
        list.add(new SoundGame(R.drawable.elec17, R.raw.fry, "Vehicle" ));
        list.add(new SoundGame(R.drawable.elec18, R.raw.fry, "Decoder" ));
        list.add(new SoundGame(R.drawable.elec19, R.raw.fry, "Remote control" ));
        list.add(new SoundGame(R.drawable.elec20, R.raw.fry, "Lamp" ));

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
