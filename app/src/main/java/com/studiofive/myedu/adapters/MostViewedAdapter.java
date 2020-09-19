package com.studiofive.myedu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.helper_class.FeaturedHelperClass;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {
    ArrayList<FeaturedHelperClass> mostViewedTopics;

    public MostViewedAdapter(ArrayList<FeaturedHelperClass> mostViewedTopics) {
        this.mostViewedTopics = mostViewedTopics;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = mostViewedTopics.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());
        holder.rating.setRating(featuredHelperClass.getRating());

    }

    @Override
    public int getItemCount() {
        return mostViewedTopics.size();
    }

    public static class MostViewedViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.mv_image)
        ImageView image;
        @BindView(R.id.mv_title)
        TextView title;
        @BindView(R.id.mv_desc)
        TextView desc;
        @BindView(R.id.mv_rating)
        RatingBar rating;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
