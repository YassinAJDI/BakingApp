package com.ajdi.yassin.bakingapp.ui.details;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.data.model.Step;
import com.ajdi.yassin.bakingapp.databinding.FragmentStepDetailBinding;
import com.ajdi.yassin.bakingapp.ui.details.videoplayer.PlayerState;
import com.ajdi.yassin.bakingapp.ui.details.videoplayer.VideoPlayerComponent;
import com.ajdi.yassin.bakingapp.utils.GlideApp;
import com.google.android.exoplayer2.ui.PlayerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

/**
 * @author Yassin Ajdi
 * @since 12/13/2018.
 */
public class StepDetailFragment extends Fragment {

    private FragmentStepDetailBinding binding;
    private RecipeDetailViewModel mViewModel;
//    public static final String EXTRA_STEP = "extra_step";

    public StepDetailFragment() {
        // Required empty public constructor
    }

    public static StepDetailFragment newInstance() {
        return new StepDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStepDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = RecipeDetailsActivity.obtainViewModel(getActivity());

        // Observe current recipe step
        mViewModel.getCurrentStep().observe(getViewLifecycleOwner(), new Observer<Step>() {
            @Override
            public void onChanged(Step step) {
                if (step != null) {
                    populateUi(step);
                }
            }
        });
    }

    private void populateUi(Step step) {
        if (!step.getVideoURL().isEmpty()) {
            PlayerView playerView = binding.videoPlayer;
            PlayerState playerState =
                    new PlayerState(0, 0, true, step.getVideoURL());
            getLifecycle().addObserver(
                    new VideoPlayerComponent(getActivity(), playerView, playerState));
            binding.imageStep.setVisibility(View.GONE);
        } else if (!step.getThumbnailURL().isEmpty()) {
            GlideApp.with(this)
                    .load(step.getThumbnailURL())
                    .into(binding.imageStep);
            binding.videoPlayer.setVisibility(View.GONE);
        } else {
            binding.videoPlayer.setVisibility(View.GONE);
            binding.imageStep.setVisibility(View.GONE);
        }

        binding.test.setText(step.getDescription());
    }
}
