package com.studiofive.myedu.activities.segments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.studiofive.myedu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetsActivity extends AppCompatActivity {
    @BindView(R.id.category_expanded_image)
    ImageView expandedImage;
    @BindView(R.id.category_expanded_title)
    TextView expandedTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
        ButterKnife.bind(this);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        int image = intent.getExtras().getInt("Image") ;

        expandedTitle.setText(Title);
        expandedImage.setImageResource(image);
    }
}