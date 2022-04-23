package com.example.tekirapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image1{

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }
}

