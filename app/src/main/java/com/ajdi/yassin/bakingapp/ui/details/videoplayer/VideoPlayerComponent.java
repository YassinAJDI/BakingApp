package com.ajdi.yassin.bakingapp.ui.details.videoplayer;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 12/16/2018.
 */
public class VideoPlayerComponent implements LifecycleObserver {

    private final Context context;
    private final PlayerView playerView;
    private SimpleExoPlayer player;
    private final String videoUrl;

    public VideoPlayerComponent(Context context, PlayerView playerView, String videoUrl) {
        this.context = context;
        this.playerView = playerView;
        this.videoUrl = videoUrl;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        if (Util.SDK_INT > 23) {
            Timber.d("onStart");
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        if (Util.SDK_INT <= 23 || player == null) {
            Timber.d("onResume");
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        if (Util.SDK_INT <= 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            Timber.d("onPause");
            releasePlayer();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        if (Util.SDK_INT > 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            Timber.d("onStop");
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;

            Timber.d("SimpleExoPlayer is released");
        }
    }

    private void initializePlayer() {
        // Initialize the player
        player = ExoPlayerFactory.newSimpleInstance(context);

        // Bind the player to the view.
//        playerView = getActivity().findViewById(R.id.video_player);
        playerView.setPlayer(player);
        playerView.requestFocus();

        // This is the MediaSource representing the media to be played.
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = buildMediaSource(uri);

        // Prepare the player with the source.
        player.prepare(mediaSource);

        // Start playback when media has buffered enough.
        player.setPlayWhenReady(true);

        Timber.d("SimpleExoPlayer is initialized");
    }

    private MediaSource buildMediaSource(Uri uri) {
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "bakingApp"));
        return new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

}
