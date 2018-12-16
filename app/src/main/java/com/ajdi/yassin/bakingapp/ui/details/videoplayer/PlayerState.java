package com.ajdi.yassin.bakingapp.ui.details.videoplayer;

/**
 * @author Yassin Ajdi
 * @since 12/16/2018.
 */
public class PlayerState {

    public int window;
    public long position;
    public boolean whenReady;
    public String videoUrl;

    public PlayerState(int window, long position, boolean whenReady, String videoUrl) {
        this.window = window;
        this.position = position;
        this.whenReady = whenReady;
        this.videoUrl = videoUrl;
    }
}
