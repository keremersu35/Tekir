package com.example.tekirapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatListModel {

    Image1 image1 = new Image1();

    @SerializedName("name")
    public String name;

    @SerializedName("image")
    @Expose
    public Image1 image = null;

    @SerializedName("life_span")
    public String lifeSpan;

    @SerializedName("origin")
    public String origin;

    @SerializedName("description")
    public String description;



}




