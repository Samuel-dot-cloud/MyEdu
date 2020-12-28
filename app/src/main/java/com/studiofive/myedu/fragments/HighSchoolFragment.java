package com.studiofive.myedu.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.ExamCategoryAdapter;
import com.studiofive.myedu.authentication.LoginActivity;
import com.studiofive.myedu.classes.ExamCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;


public class HighSchoolFragment extends Fragment {
    @BindView(R.id.category_grid_high_school)
    GridView categoryView;
    private Unbinder unbinder;
    private Context mContext;
    private FirebaseFirestore mFirestore;
    public static List<ExamCategory> examCategoryList = new ArrayList<>();
    private static String TAG = "High School Fragment";


    public HighSchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_high_school, container, false);
        unbinder = ButterKnife.bind(this, view);
        mFirestore = FirebaseFirestore.getInstance();

        ExamCategoryAdapter adapter = new ExamCategoryAdapter(examCategoryList);
        categoryView.setAdapter(adapter);
        //loadCategories();
//        loadExamCategories();
         return view;
    }

//    private void loadCategories() {
//        examCategoryList.clear();
//        examCategoryList.add(new ExamCategory("1", "Calculus", 20));
//        examCategoryList.add(new ExamCategory("2", "Geometry", 30));
//        examCategoryList.add(new ExamCategory("3", "Algebra", 5));
//        examCategoryList.add(new ExamCategory("4", "Chemistry", 10));
//        examCategoryList.add(new ExamCategory("5", "Physics", 7));
//        examCategoryList.add(new ExamCategory("6", "Engineering Drawing", 9));
//        examCategoryList.add(new ExamCategory("7", "Development Studies", 15));
//    }

//    private void loadExamCategories(){
//        examCategoryList.clear();
//        mFirestore.collection("Exams").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, QueryDocumentSnapshot> documentList = new ArrayMap<>();
//                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
//                            documentList.put(doc.getId(), doc);
//                        }
//
//                        QueryDocumentSnapshot categoryListDoc = documentList.get("Categories");
//                        long catCount = categoryListDoc.getLong("Count");
//
//                        for (int i = 1; i <= catCount; i++){
//                            String catID = categoryListDoc.getString("Cat" + String.valueOf(i) + "_ID");
//                            QueryDocumentSnapshot catDoc = documentList.get(catID);
//                            int noOfTests = catDoc.getLong("No_Of_Tests").intValue();
//                            String catName = catDoc.getString("Name");
//                            examCategoryList.add(new ExamCategory(catID, catName, noOfTests));
//                            Log.d(TAG, "Display");
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "An error");
//                        Toasty.error(mContext.getApplicationContext() , e.getMessage(), Toast.LENGTH_SHORT,true).show();
//                    }
//                });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}