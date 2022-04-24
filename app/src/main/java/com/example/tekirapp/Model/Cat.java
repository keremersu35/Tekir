package com.example.tekirapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cat {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "origin")
    public String origin;

    @ColumnInfo(name = "lifespan")
    public String lifeSpan;

    @ColumnInfo(name = "fav")
    public boolean fav;

    public Cat(String name, String url, String description, String origin, String lifeSpan, boolean fav) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.origin = origin;
        this.lifeSpan = lifeSpan;
        this.fav = fav;
    }
}
