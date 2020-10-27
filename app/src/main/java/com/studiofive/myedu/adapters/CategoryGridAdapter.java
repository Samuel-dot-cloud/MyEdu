package com.studiofive.myedu.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.activities.segments.SetsActivity;

import java.util.List;
import java.util.Random;

public class CategoryGridAdapter extends BaseAdapter {

    private List<String> categoryList;

    public CategoryGridAdapter(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_category, parent, false);
        }else {
            view = convertView;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), SetsActivity.class);
                intent.putExtra("Category", categoryList.get(position));
                parent.getContext().startActivity(intent);
            }
        });

        ((TextView)view.findViewById(R.id.quiz_category_text)).setText(categoryList.get(position));

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        view.setBackgroundColor(color);

        return view;
    }
}
