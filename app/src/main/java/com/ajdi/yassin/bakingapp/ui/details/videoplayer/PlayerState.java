package com.ajdi.yassin.bakingapp.ui.details.videoplayer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Yassin Ajdi
 * @since 12/16/2018.
 */
public class PlayerState implements Parcelable {

    public int window;
    public long position;
    public boolean whenReady;
    public String videoUrl;

    public PlayerState() {
    }

    public PlayerState(int window, long position, boolean whenReady, String videoUrl) {
        this.window = window;
        this.position = position;
        this.whenReady = whenReady;
        this.videoUrl = videoUrl;
    }

    protected PlayerState(Parcel in) {
        window = in.readInt();
        position = in.readLong();
        whenReady = in.readByte() != 0;
        videoUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(window);
        dest.writeLong(position);
        dest.writeByte((byte) (whenReady ? 1 : 0));
        dest.writeString(videoUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlayerState> CREATOR = new Creator<PlayerState>() {
        @Override
        public PlayerState createFromParcel(Parcel in) {
            return new PlayerState(in);
        }

        @Override
        public PlayerState[] newArray(int size) {
            return new PlayerState[size];
        }
    };
}
