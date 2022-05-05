package com.example.tekirapp.Services;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tekirapp.Model.Cat;

import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CatDao {

    @Query("SELECT * FROM Cat WHERE fav = 1")
    Flowable<List<Cat>> getAll();

    @Insert
    Completable insert(Cat cat);

    @Delete
    Completable delete(Cat cat);
}
