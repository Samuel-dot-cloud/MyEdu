package com.studiofive.myedu.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.activities.segments.SetsActivity;
import com.studiofive.myedu.classes.Quiz;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryGridAdapter extends RecyclerView.Adapter<CategoryGridAdapter.MyViewHolder>{
    private Context mContext;
    private List<Quiz> mQuiz;

    public CategoryGridAdapter(Context mContext, List<Quiz> mQuiz) {
        this.mContext = mContext;
        this.mQuiz = mQuiz;
    }

    @NonNull
    @Override
    public CategoryGridAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.quiz_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryGridAdapter.MyViewHolder holder, int position) {
        holder.quiz_category_title.setText(mQuiz.get(position).getTitle());
        holder.quiz_category_image.setImageResource(mQuiz.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SetsActivity.class);

                // passing data to the book activity
                intent.putExtra("Title",mQuiz.get(position).getTitle());
                intent.putExtra("Image",mQuiz.get(position).getImage());
                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuiz.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.quiz_category_image)
        ImageView quiz_category_image;
        @BindView(R.id.quiz_category_text)
        TextView quiz_category_title;

        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cardView = itemView.findViewById(R.id.category_quiz_design);
        }
    }
}
