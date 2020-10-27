package com.studiofive.myedu.activities.segments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.SetsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetsActivity extends AppCompatActivity {
//    @BindView(R.id.category_expanded_image)
//    ImageView expandedImage;
    @BindView(R.id.category_expanded_title)
    TextView expandedTitle;
    @BindView(R.id.sets_grid_view)
    GridView setsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
        ButterKnife.bind(this);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Category");

        expandedTitle.setText(Title);
//        expandedImage.setImageResource(image);

        SetsAdapter setsAdapter = new SetsAdapter(6);
        setsView.setAdapter(setsAdapter);
    }
}