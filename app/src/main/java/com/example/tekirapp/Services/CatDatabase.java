package com.example.tekirapp.Services;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tekirapp.Model.Cat;

@Database(entities = {Cat.class}, version= 1)
public abstract class CatDatabase extends RoomDatabase {
    public abstract CatDao catDao();

}
