package com.example.tekirapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CatListModel {

    Image1 image1 = new Image1();

    @SerializedName("name")
    public String name;

    @SerializedName("image")
    @Expose
    public Image1 image = null;

}




