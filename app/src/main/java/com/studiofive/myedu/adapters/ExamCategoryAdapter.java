package com.studiofive.myedu.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.ExamCategory;

import java.util.List;

public class ExamCategoryAdapter extends BaseAdapter {
    private List<ExamCategory> categoryList;

    public ExamCategoryAdapter(List<ExamCategory> categoryList) {
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_category_layout, parent, false);
        }else {
            view = convertView;
        }

        TextView categoryName = view.findViewById(R.id.exam_category_name);
        TextView noOfTests = view.findViewById(R.id.no_of_tests);

        categoryName.setText(categoryList.get(position).getName());
        noOfTests.setText(String.valueOf(categoryList.get(position).getNoOfTests()));

        return view;
    }
}
