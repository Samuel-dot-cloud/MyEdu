package com.studiofive.myedu.activities.segments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.studiofive.myedu.R;
import com.studiofive.myedu.adapters.TestsAdapter;
import com.studiofive.myedu.classes.TestModel;
import com.studiofive.myedu.fragments.HighSchoolFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestsActivity extends AppCompatActivity {
    @BindView(R.id.test_recycler_view)
    RecyclerView testsView;
    @BindView(R.id.test_toolbar)
    Toolbar toolbar;
    private List<TestModel>testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        int category_index = getIntent().getIntExtra("category_index", 0);
        getSupportActionBar().setTitle(HighSchoolFragment.examCategoryList.get(category_index).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        testsView.setLayoutManager(layoutManager);

        loadTestData();

        TestsAdapter adapter = new TestsAdapter(testList);
        testsView.setAdapter(adapter);

    }

    private void loadTestData() {
        testList = new ArrayList<>();
        testList.add(new TestModel("1", 50, 25));
        testList.add(new TestModel("2", 100, 45));
        testList.add(new TestModel("3", 60, 30));
        testList.add(new TestModel("4", 40, 15));
        testList.add(new TestModel("5", 70, 20));
        testList.add(new TestModel("6", 80, 25));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            TestsActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}