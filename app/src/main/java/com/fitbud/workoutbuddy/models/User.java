package com.fitbud.workoutbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rishabh Bhatia on 5/22/2016.
 */
public class User {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("photo_url")
    private String photoUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

