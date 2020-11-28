package com.studiofive.myedu.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.SoundGame;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SoundGameAdapter extends RecyclerView.Adapter<SoundGameAdapter.SoundGameViewHolder> {
    private ArrayList<SoundGame> dataSet;
    private Context mContext;
    private MediaPlayer mPlayer;
    private boolean imageStateVolume = false;


    public SoundGameAdapter(ArrayList<SoundGame> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SoundGameAdapter.SoundGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_layout, parent, false);
        return new SoundGameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundGameAdapter.SoundGameViewHolder holder, int position) {
        SoundGame object = dataSet.get(position);
        holder.imageView.setImageResource(object.getImage());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageStateVolume){
                    if (mPlayer.isPlaying()){
                        mPlayer.stop();
                    }
                    imageStateVolume = false;
                }else {
                    mPlayer = MediaPlayer.create(mContext, object.getSound());
                    mPlayer.setLooping(false);
                    mPlayer.start();
                    imageStateVolume = true;
                    Toasty.info(mContext.getApplicationContext(), object.getText(), Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class SoundGameViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sound_game_image)
        ImageView imageView;
        public SoundGameViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
