package com.ajdi.yassin.bakingapp.ui.details.steps;

import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.data.model.Step;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi
 * @since 12/13/2018.
 */
public class StepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Step> mStepList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StepViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Step step = mStepList.get(position);
        ((StepViewHolder) holder).bindTo(step, position);
    }

    @Override
    public int getItemCount() {
        return mStepList != null ? mStepList.size() : 0;
    }

    public void submitList(List<Step> steps) {
        mStepList = steps;
        notifyDataSetChanged();
    }
}
