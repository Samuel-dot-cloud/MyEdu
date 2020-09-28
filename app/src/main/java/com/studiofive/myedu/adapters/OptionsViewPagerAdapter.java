package com.studiofive.myedu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.helper_class.OptionItem;

import java.util.List;

public class OptionsViewPagerAdapter extends PagerAdapter {
    Context mContext;
    List<OptionItem> mOptionsScreen;

    public OptionsViewPagerAdapter(Context mContext, List<OptionItem> mOptionsScreen) {
        this.mContext = mContext;
        this.mOptionsScreen = mOptionsScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View optionsScreen = inflater.inflate(R.layout.preschool_options_design, null);
        ImageView imgSlide = optionsScreen.findViewById(R.id.options_image);
        TextView title = optionsScreen.findViewById(R.id.options_title);

        imgSlide.setImageResource(mOptionsScreen.get(position).getImage());
        title.setText(mOptionsScreen.get(position).getTitle());

        optionsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    Log.d("Adapter", "Position 0");
                }
                if (position == 1){
                    Log.d("Adapter", "Position 1");
                }
            }
        });
        container.addView(optionsScreen,0);

        return optionsScreen;
    }

    @Override
    public int getCount() {
        return mOptionsScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
      return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
