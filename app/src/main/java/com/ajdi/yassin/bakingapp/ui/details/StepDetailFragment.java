package com.ajdi.yassin.bakingapp.ui.details;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Step;
import com.ajdi.yassin.bakingapp.databinding.FragmentStepDetailBinding;
import com.ajdi.yassin.bakingapp.utils.GlideApp;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
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

    private FragmentStepDetailBinding binding;
    private RecipeDetailViewModel mViewModel;
    private SimpleExoPlayer player;
    private PlayerView playerView;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    public static StepDetailFragment newInstance() {
        return new StepDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
//            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
//            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
        }
    }

    private void populateUi(Step step) {
        if (!step.getVideoURL().isEmpty()) {
            initializePlayer(step.getVideoURL());

            binding.imageStep.setVisibility(View.GONE);
        } else if (!step.getThumbnailURL().isEmpty()) {
            ImageView imageView = getActivity().findViewById(R.id.image_step);
            Timber.d(step.getThumbnailURL());
            GlideApp.with(this)
                    .load(step.getThumbnailURL())
                    .into(imageView);

            binding.videoPlayer.setVisibility(View.GONE);
        } else {
            binding.videoPlayer.setVisibility(View.GONE);
            binding.imageStep.setVisibility(View.GONE);
        }

        binding.test.setText(step.getDescription());
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
        player = ExoPlayerFactory.newSimpleInstance(getActivity());

        // Bind the player to the view.
        playerView = getActivity().findViewById(R.id.video_player);
        playerView.setPlayer(player);
        playerView.requestFocus();

        // This is the MediaSource representing the media to be played.
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = buildMediaSource(uri);

        // Prepare the player with the source.
        player.prepare(mediaSource);

        // Start playback when media has buffered enough.
        player.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri uri) {
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "bakingApp"));
        return new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }
}
