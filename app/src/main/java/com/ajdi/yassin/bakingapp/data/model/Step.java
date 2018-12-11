package com.ajdi.yassin.bakingapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class Step {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("videoURL")
    @Expose
    private String videoURL;

    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
