package com.example.tekirapp.Services;

import com.example.tekirapp.Model.CatListModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatApi {

    @GET("breeds")
    Observable<List<CatListModel>> getData();

    @GET("breeds/search")
    Observable<List<CatListModel>> getQuery(
            @Query("q") String name
    );
}
