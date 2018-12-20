package com.ajdi.yassin.bakingapp.ui.recipedetail.videoplayer;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
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
    private PlayerState playerState;

    public VideoPlayerComponent(Context context, PlayerView playerView, PlayerState playerState) {
        this.context = context;
        this.playerView = playerView;
        this.playerState = playerState;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        if (Util.SDK_INT > 23) {
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
            Timber.d("SimpleExoPlayer is started");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
            Timber.d("SimpleExoPlayer is resumed");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        if (Util.SDK_INT <= 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
            Timber.d("SimpleExoPlayer is paused");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        if (Util.SDK_INT > 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
            Timber.d("SimpleExoPlayer is stopped");
        }
    }

    private void releasePlayer() {
        if (player != null) {
            updateStartPosition();
            player.release();
            player = null;

            Timber.d("SimpleExoPlayer is released");
        }
    }

    private void initializePlayer() {
        if (player == null) {
            // Initialize the player
            player = ExoPlayerFactory.newSimpleInstance(context,
                    new DefaultRenderersFactory(context),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());

            // Bind the player to the view.
            playerView.setPlayer(player);
            playerView.requestFocus();

            // This is the MediaSource representing the media to be played.
            Uri uri = Uri.parse(playerState.videoUrl);
            MediaSource mediaSource = buildMediaSource(uri);

            // Start playback when media has buffered enough.
            player.setPlayWhenReady(playerState.whenReady);
            boolean haveResumePosition = playerState.window != C.INDEX_UNSET;
            if (haveResumePosition) {
                Timber.d("Have Resume position true! " + playerState.window);
                player.seekTo(playerState.window, playerState.position);
            }

            player.prepare(mediaSource, !haveResumePosition, false);
            Timber.d("SimpleExoPlayer created");
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "bakingApp"));
        return new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    private void updateStartPosition() {
        if (player != null) {
            playerState.whenReady = player.getPlayWhenReady();
            playerState.window = player.getCurrentWindowIndex();
            playerState.position = Math.max(0, player.getContentPosition());
        }
    }

}
