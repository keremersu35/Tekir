package com.example.tekirapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class MainActivity extends AppCompatActivity {

    ArrayList<CatListModel> catModels;
    private String BASE_URL = "https://api.thecatapi.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    HomepageAdapter HomepageAdapter;
    Image1 image;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewHome);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();
    }

    private void loadData() {

        final CatApi catApi = retrofit.create(CatApi.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(catApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, throwable -> throwable.getLocalizedMessage()));

    }

    private void handleResponse(List<CatListModel> catModelList) {
        catModels = new ArrayList<>(catModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        HomepageAdapter = new HomepageAdapter(catModels);
        recyclerView.setAdapter(HomepageAdapter);
        HomepageAdapter.notifyDataSetChanged();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                HomepageAdapter.getFilter().filter(newText);
                HomepageAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}