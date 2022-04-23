package com.example.tekirapp;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CatApi {

    @GET("breeds")
    Observable<List<CatListModel>> getData();
}
