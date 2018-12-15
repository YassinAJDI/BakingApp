package com.ajdi.yassin.bakingapp.ui.details;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 12/13/2018.
 */
public class StepDetailFragment extends Fragment {

    private RecipeDetailViewModel mViewModel;
    private SimpleExoPlayer player;

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

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void populateUi(Step step) {
        if (!step.getVideoURL().isEmpty()) {
            initializePlayer(step.getVideoURL());
        }
        TextView textView = getActivity().findViewById(R.id.test);
        textView.setText(step.getDescription());
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            Timber.d("SimpleExoPlayer is released");
        }
    }

    private void initializePlayer(String videoUrl) {
        // Initialize the player
        player = ExoPlayerFactory.newSimpleInstance(getActivity(), new DefaultTrackSelector());

        // Initialize ExoPlayerView
        PlayerView playerView = getActivity().findViewById(R.id.video_player);
        playerView.setPlayer(player);

        // This is the MediaSource representing the media to be played.
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = buildMediaSource(uri);

        // Prepare the player with the source.
        player.prepare(mediaSource);

        // Start playback when media has buffered enough.
        player.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("bakingApp"))
                .createMediaSource(uri);
    }
}
