package com.studiofive.myedu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.TestModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.ViewHolder> {
    private List<TestModel> testList;

    public TestsAdapter(List<TestModel> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public TestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestsAdapter.ViewHolder holder, int position) {
        int progress = testList.get(position).getTopScore();
        holder.setData(position, progress);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.testNo)
        TextView testNo;
        @BindView(R.id.test_progress)
        ProgressBar testProgress;
        @BindView(R.id.test_score)
        TextView topScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setData(int position, int progress){
            testNo.setText("Test No : " + String.valueOf(position + 1));
            topScore.setText(String.valueOf(progress) + " %");
            testProgress.setProgress(progress);
        }
    }
}
