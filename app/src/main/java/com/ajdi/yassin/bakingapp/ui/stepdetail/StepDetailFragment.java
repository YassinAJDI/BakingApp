package com.ajdi.yassin.bakingapp.ui.stepdetail;


import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.local.model.Step;
import com.ajdi.yassin.bakingapp.databinding.FragmentStepDetailBinding;
import com.ajdi.yassin.bakingapp.ui.recipedetail.videoplayer.PlayerState;
import com.ajdi.yassin.bakingapp.ui.recipedetail.videoplayer.VideoPlayerComponent;
import com.ajdi.yassin.bakingapp.utils.GlideApp;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 12/13/2018.
 */
public class StepDetailFragment extends Fragment {

    public static final String KEY_STEP_DATA = "step_data";
    private static final String KEY_PLAYER_STATE = "KEY_PLAYER_STATE";

    private FragmentStepDetailBinding binding;
    private StepDetailViewModel mViewModel;
    private PlayerView playerView;
    private PlayerState playerState = new PlayerState();
    private Step step;
    private boolean isLandscape;

    private boolean isTablet;

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
        Timber.d("onCreateView");

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true;
            Timber.d("Landscape");
        }
        // determine which layout we are in (tablet or phone)
        if (getActivity().findViewById(R.id.fragment_step_detail) != null
                && getActivity().findViewById(R.id.fragment_recipe_detail) != null) {
            isTablet = true;
        }

        binding = FragmentStepDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = StepDetailActivity.obtainViewModel(getActivity());

        Timber.d("onActivityCreated");
        if (savedInstanceState != null) {
            step = savedInstanceState.getParcelable(KEY_STEP_DATA);
            playerState = savedInstanceState.getParcelable(KEY_PLAYER_STATE);
        } else {
            step = getArguments().getParcelable(KEY_STEP_DATA);
            clearStartPosition();
        }
        populateUi();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(KEY_STEP_DATA, step);
        outState.putParcelable(KEY_PLAYER_STATE, playerState);

        super.onSaveInstanceState(outState);
    }

    private void clearStartPosition() {
        playerState.whenReady = true;
        playerState.window = C.INDEX_UNSET;
        playerState.position = C.TIME_UNSET;
    }

    private void populateUi() {
        Timber.d("populateUi");
        boolean hasVideo = !step.getVideoURL().isEmpty();
        if (hasVideo) {
            // initialize and show video player
            playerView = binding.stepDetailContent.videoPlayer;
            playerState.videoUrl = step.getVideoURL();
            getLifecycle().addObserver(
                    new VideoPlayerComponent(getActivity(), playerView, playerState));

            if (isLandscape && !isTablet) {
                hideShow(binding.stepDetailContent.stepDetailsHolder, false);
                hideShow(binding.navigationButtonsContainer, false);
                hideShow(getActivity().findViewById(R.id.toolbar), false);
                removeConstraint();
                expandVideoView();
                hideSystemUi();
            }

            hideShow(binding.stepDetailContent.videoPlayer, true);
        } else {
            hideShow(binding.stepDetailContent.videoPlayer, false);
        }

        if (!step.getThumbnailURL().isEmpty()) {
            // Load and show Image
            GlideApp.with(this)
                    .load(step.getThumbnailURL())
                    .placeholder(R.color.colorAccent)
                    .into(binding.stepDetailContent.imageStep);
            hideShow(binding.stepDetailContent.imageStep, false);
        } else {
            hideShow(binding.stepDetailContent.imageStep, false);
        }

        if (!isTablet && (!isLandscape || !hasVideo)) {
            if (mViewModel.hasNext()) {
                hideShow(binding.buttonNext, true);
            } else {
                hideShow(binding.buttonNext, false);
            }

            if (mViewModel.hasPrevious()) {
                hideShow(binding.buttonPrev, true);
            } else {
                hideShow(binding.buttonPrev, false);
            }

            // click events
            binding.buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.nextStep();
                }
            });
            binding.buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.previousStep();
                }
            });
        }

        binding.stepDetailContent.tvTitle.setText(step.getShortDescription());
        binding.stepDetailContent.textDescription.setText(step.getDescription());
        binding.stepDetailContent.executePendingBindings();
        binding.executePendingBindings();
    }

    private void removeConstraint() {
        // step details
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout layout = binding.stepDetailsContainer;
        set.clone(layout);
        set.clear(R.id.step_detail_content, ConstraintSet.BOTTOM);
        set.connect(R.id.step_detail_content, ConstraintSet.BOTTOM, R.id.step_details_container, ConstraintSet.BOTTOM);
        set.applyTo(layout);

        // video player constrains
        set = new ConstraintSet();
        ConstraintLayout playerContainer = binding.stepDetailContent.videoContainer;
        set.clone(playerContainer);
        set.clear(R.id.video_player, ConstraintSet.END);
        set.clear(R.id.video_player, ConstraintSet.START);
        set.clear(R.id.video_player, ConstraintSet.TOP);
        set.applyTo(playerContainer);
    }

    private void expandVideoView() {
        playerView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        playerView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

    }

    private void hideShow(View view, boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
