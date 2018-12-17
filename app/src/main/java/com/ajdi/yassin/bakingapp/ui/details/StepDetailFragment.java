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
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ui.PlayerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author Yassin Ajdi
 * @since 12/13/2018.
 */
public class StepDetailFragment extends Fragment {

    private static final String KEY_WINDOW = "window";
    private static final String KEY_POSITION = "position";
    private static final String KEY_AUTO_PLAY = "auto_play";
    public static final String KEY_STEP_DATA = "step_data";

    private FragmentStepDetailBinding binding;
    private RecipeDetailViewModel mViewModel;
    private PlayerState playerState;
    private Step step;

    private boolean startAutoPlay;
    private int startWindow;
    private long startPosition;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    public static StepDetailFragment newInstance(Step step) {
        StepDetailFragment fragment = new StepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_STEP_DATA, step);
        fragment.setArguments(args);
        return fragment;
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
//        mViewModel = RecipeDetailsActivity.obtainViewModel(getActivity());

        if (savedInstanceState != null) {
            step = savedInstanceState.getParcelable(KEY_STEP_DATA);
            startAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
            startWindow = savedInstanceState.getInt(KEY_WINDOW);
            startPosition = savedInstanceState.getLong(KEY_POSITION);
        } else {
            step = getArguments().getParcelable(KEY_STEP_DATA);
            clearStartPosition();
        }
        populateUi();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(KEY_STEP_DATA, step);
        outState.putBoolean(KEY_AUTO_PLAY, playerState.whenReady);
        outState.putInt(KEY_WINDOW, playerState.window);
        outState.putLong(KEY_POSITION, playerState.position);

        super.onSaveInstanceState(outState);
    }

    private void clearStartPosition() {
        startAutoPlay = true;
        startWindow = C.INDEX_UNSET;
        startPosition = C.TIME_UNSET;
    }

    private void populateUi() {
        if (!step.getVideoURL().isEmpty()) {
            PlayerView playerView = binding.videoPlayer;
            playerState = new PlayerState(startWindow, startPosition, startAutoPlay, step.getVideoURL());
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
        binding.executePendingBindings();
    }
}
