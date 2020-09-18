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

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> featuredTopics;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredTopics) {
        this.featuredTopics = featuredTopics;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = featuredTopics.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());
        holder.rating.setRating(featuredHelperClass.getRating());
    }

    @Override
    public int getItemCount() {
        return featuredTopics.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.featured_image)
        ImageView image;
        @BindView(R.id.featured_title)
        TextView title;
        @BindView(R.id.featured_desc)
        TextView desc;
        @BindView(R.id.featured_rating)
        RatingBar rating;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
