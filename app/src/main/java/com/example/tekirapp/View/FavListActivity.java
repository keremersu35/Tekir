package com.example.tekirapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.tekirapp.Adapter.FavListAdaptor;
import com.example.tekirapp.Model.Cat;
import com.example.tekirapp.R;
import com.example.tekirapp.Services.CatDao;
import com.example.tekirapp.Services.CatDatabase;
import com.example.tekirapp.databinding.ActivityFavListBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavListActivity extends AppCompatActivity {

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    ArrayList<Cat> places;
    RecyclerView recyclerView;
    ArrayList<Cat> CatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);

        recyclerView = findViewById(R.id.recyclerViewFav);
        CatList = new ArrayList<>();

        CatDatabase db = Room.databaseBuilder(getApplicationContext(),
                CatDatabase.class, "Cats").allowMainThreadQueries().build();

        CatDao catDao = db.catDao();

        mDisposable.add(catDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, throwable -> throwable.getLocalizedMessage()));

    }

    private void handleResponse(List<Cat> catList) {
        System.out.println("kerem"+ catList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FavListAdaptor favListAdaptor = new FavListAdaptor(catList);
        recyclerView.setAdapter(favListAdaptor);

    }
}
