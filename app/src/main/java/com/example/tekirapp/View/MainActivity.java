package com.example.tekirapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.tekirapp.Services.CatApi;
import com.example.tekirapp.Model.CatListModel;
import com.example.tekirapp.Adapter.HomepageAdapter;
import com.example.tekirapp.R;
import com.example.tekirapp.Services.RetrofitInitializer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ArrayList<CatListModel> catModels;
    RecyclerView recyclerView;
    com.example.tekirapp.Adapter.HomepageAdapter HomepageAdapter;
    SearchView searchView;
    RetrofitInitializer retrofitInitializer;
    String temp;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recyclerViewHome);
        searchView = findViewById(R.id.searchView);
        retrofitInitializer = new RetrofitInitializer();
        retrofitInitializer.Initialize();

        loadData(temp);
        Search();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_fav) {
            Intent intent1 = new Intent(this,FavListActivity.class);
            this.startActivity(intent1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData(String query){

        if(query == null || query.isEmpty()){
            final CatApi catApi = retrofitInitializer.retrofit.create(CatApi.class);
            compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(catApi.getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse, throwable -> throwable.getLocalizedMessage()));
        }else{
            final CatApi catApi = retrofitInitializer.retrofit.create(CatApi.class);
            compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(catApi.getQuery(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse, throwable -> throwable.getLocalizedMessage()));
        }
    }

    private void handleResponse(List<CatListModel> catModelList) {
        catModels = new ArrayList<>(catModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        HomepageAdapter = new HomepageAdapter(catModels);
        recyclerView.setAdapter(HomepageAdapter);
        HomepageAdapter.notifyDataSetChanged();
    }

    private void Search(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 0){
                    loadData(query);
                }else{
                    loadData(query);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {

                if(query.length() > 0){
                    loadData(query);
                }else{
                    loadData(query);
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}