package com.ajdi.yassin.bakingapp.ui.details;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Step;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

/**
 * @author Yassin Ajdi
 * @since 12/13/2018.
 */
public class StepDetailFragment extends Fragment {

    private RecipeDetailViewModel mViewModel;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    public static StepDetailFragment newInstance() {
        return new StepDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = RecipeDetailsActivity.obtainViewModel(getActivity());
        mViewModel.getCurrentStep().observe(getViewLifecycleOwner(), new Observer<Step>() {
            @Override
            public void onChanged(Step step) {
                TextView textView = getActivity().findViewById(R.id.test);
                textView.setText(step.getDescription());
            }
        });
    }
}
