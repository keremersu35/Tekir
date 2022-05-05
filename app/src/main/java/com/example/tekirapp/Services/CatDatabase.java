package com.example.tekirapp.Services;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tekirapp.Model.Cat;

@Database(entities = {Cat.class}, version= 1)
public abstract class CatDatabase extends RoomDatabase {
    public abstract CatDao catDao();
    }

