package com.studiofive.myedu.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.CategoriesAdapter;
import com.studiofive.myedu.adapters.FeaturedAdapter;
import com.studiofive.myedu.adapters.MostViewedAdapter;
import com.studiofive.myedu.classes.helper_class.CategoriesHelperClass;
import com.studiofive.myedu.classes.helper_class.FeaturedHelperClass;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.featured_recycler)
    RecyclerView featuredRecycler;
    @BindView(R.id.most_viewed_recyclerview)
    RecyclerView mostViewedRecycler;
    @BindView(R.id.categories_recyclerview)
    RecyclerView categoriesRecycler;

    @SuppressLint("ResourceType")
    @BindView(R.layout.categories_card_design)
    CardView categoryDesign;

    private Unbinder unbinder;
    private Context mContext;
    RecyclerView.Adapter adapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        Random r = new Random();
        int[] colors = new int[]{0xFF616261,0xFF131313, 0xFF125FF8, 0xFFFFEA00, 0xFF5D4037};

//        getRandom(r, colors);
        featuredRecyclerMethod();
        mostViewedRecyclerMethod();
        categoriesRecyclerMethod();

        return view;
    }

    private void featuredRecyclerMethod() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredTopics = new ArrayList<>();
        featuredTopics.add(new FeaturedHelperClass(R.drawable.calculus, "Calculus", "The wonderful world of calculus", 4));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.design, "Drawing And Design", "The wonderful world of  Design", 4));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.computer, "Computer Studies", "The wonderful world of computer studies", 3));
        featuredTopics.add(new FeaturedHelperClass(R.drawable.chemistry, "Chemistry", "The wonderful world of chemistry", 5));

        adapter = new FeaturedAdapter(featuredTopics);
        featuredRecycler.setAdapter(adapter);
    }

    private void mostViewedRecyclerMethod() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        ArrayList<FeaturedHelperClass> mostViewedTopics = new ArrayList<>();
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.calculus, "Calculus", "The wonderful world of calculus", 4));
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.design, "Drawing And Design", "The wonderful world of  Design", 4));
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.computer, "Computer Studies", "The wonderful world of computer studies", 4));
        mostViewedTopics.add(new FeaturedHelperClass(R.drawable.chemistry, "Chemistry", "The wonderful world of chemistry", 4));

        adapter = new MostViewedAdapter(mostViewedTopics);
        mostViewedRecycler.setAdapter(adapter);
    }

    private void categoriesRecyclerMethod(){
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        ArrayList<CategoriesHelperClass> categories = new ArrayList<>();
        categories.add(new CategoriesHelperClass(R.drawable.chemistry, "Chemistry"));
        categories.add(new CategoriesHelperClass(R.drawable.calculus, "Calculus"));
        categories.add(new CategoriesHelperClass(R.drawable.design, "Design"));
        categories.add(new CategoriesHelperClass(R.drawable.computer, "Computer"));

//        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[] {0xFFFFEA00, 0xFF5D4037});

        //  google's material design colours from    , 254 colors
//  http://www.google.com/design/spec/style/color.html#color-ui-color-palette

        String[] mColors = {
                "FFEBEE", "FFCDD2", "EF9A9A", "E57373", "EF5350", "F44336", "E53935",        //reds
                "D32F2F", "C62828", "B71C1C", "FF8A80", "FF5252", "FF1744", "D50000",
                "FCE4EC", "F8BBD0", "F48FB1", "F06292", "EC407A", "E91E63", "D81B60",        //pinks
                "C2185B", "AD1457", "880E4F", "FF80AB", "FF4081", "F50057", "C51162",
                "F3E5F5", "E1BEE7", "CE93D8", "BA68C8", "AB47BC", "9C27B0", "8E24AA",        //purples
                "7B1FA2", "6A1B9A", "4A148C", "EA80FC", "E040FB", "D500F9", "AA00FF",
                "EDE7F6", "D1C4E9", "B39DDB", "9575CD", "7E57C2", "673AB7", "5E35B1",        //deep purples
                "512DA8", "4527A0", "311B92", "B388FF", "7C4DFF", "651FFF", "6200EA",
                "E8EAF6", "C5CAE9", "9FA8DA", "7986CB", "5C6BC0", "3F51B5", "3949AB",        //indigo
                "303F9F", "283593", "1A237E", "8C9EFF", "536DFE", "3D5AFE", "304FFE",
                "E3F2FD", "BBDEFB", "90CAF9", "64B5F6", "42A5F5", "2196F3", "1E88E5",        //blue
                "1976D2", "1565C0", "0D47A1", "82B1FF", "448AFF", "2979FF", "2962FF",
                "E1F5FE", "B3E5FC", "81D4fA", "4fC3F7", "29B6FC", "03A9F4", "039BE5",        //light blue
                "0288D1", "0277BD", "01579B", "80D8FF", "40C4FF", "00B0FF", "0091EA",
                "E0F7FA", "B2EBF2", "80DEEA", "4DD0E1", "26C6DA", "00BCD4", "00ACC1",        //cyan
                "0097A7", "00838F", "006064", "84FFFF", "18FFFF", "00E5FF", "00B8D4",
                "E0F2F1", "B2DFDB", "80CBC4", "4DB6AC", "26A69A", "009688", "00897B",        //teal
                "00796B", "00695C", "004D40", "A7FFEB", "64FFDA", "1DE9B6", "00BFA5",
                "E8F5E9", "C8E6C9", "A5D6A7", "81C784", "66BB6A", "4CAF50", "43A047",        //green
                "388E3C", "2E7D32", "1B5E20", "B9F6CA", "69F0AE", "00E676", "00C853",
                "F1F8E9", "DCEDC8", "C5E1A5", "AED581", "9CCC65", "8BC34A", "7CB342",        //light green
                "689F38", "558B2F", "33691E", "CCFF90", "B2FF59", "76FF03", "64DD17",
                "F9FBE7", "F0F4C3", "E6EE9C", "DCE775", "D4E157", "CDDC39", "C0CA33",        //lime
                "A4B42B", "9E9D24", "827717", "F4FF81", "EEFF41", "C6FF00", "AEEA00",
                "FFFDE7", "FFF9C4", "FFF590", "FFF176", "FFEE58", "FFEB3B", "FDD835",        //yellow
                "FBC02D", "F9A825", "F57F17", "FFFF82", "FFFF00", "FFEA00", "FFD600",
                "FFF8E1", "FFECB3", "FFE082", "FFD54F", "FFCA28", "FFC107", "FFB300",        //amber
                "FFA000", "FF8F00", "FF6F00", "FFE57F", "FFD740", "FFC400", "FFAB00",
                "FFF3E0", "FFE0B2", "FFCC80", "FFB74D", "FFA726", "FF9800", "FB8C00",        //orange
                "F57C00", "EF6C00", "E65100", "FFD180", "FFAB40", "FF9100", "FF6D00",
                "FBE9A7", "FFCCBC", "FFAB91", "FF8A65", "FF7043", "FF5722", "F4511E",        //deep orange
                "E64A19", "D84315", "BF360C", "FF9E80", "FF6E40", "FF3D00", "DD2600",
                "EFEBE9", "D7CCC8", "BCAAA4", "A1887F", "8D6E63", "795548", "6D4C41",        //brown
                "5D4037", "4E342E", "3E2723",
                "FAFAFA", "F5F5F5", "EEEEEE", "E0E0E0", "BDBDBD", "9E9E9E", "757575",        //grey
                "616161", "424242", "212121",
                "ECEFF1", "CFD8DC", "B0BBC5", "90A4AE", "78909C", "607D8B", "546E7A",        //blue grey
                "455A64", "37474F", "263238"
        };

        // generate a random number
        int i = new Random().nextInt(254);

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(Color.parseColor ("#"+mColors[i]));
        categoryDesign.setBackground(shape);


        adapter = new CategoriesAdapter(categories);
        categoriesRecycler.setAdapter(adapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}