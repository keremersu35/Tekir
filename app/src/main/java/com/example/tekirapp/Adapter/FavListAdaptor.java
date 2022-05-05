package com.example.tekirapp.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tekirapp.Model.Cat;
import com.example.tekirapp.Model.CatListModel;
import com.example.tekirapp.R;
import com.example.tekirapp.Services.CatDao;
import com.example.tekirapp.Services.CatDatabase;
import com.example.tekirapp.View.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

import static android.content.Context.MODE_PRIVATE;

public class FavListAdaptor extends RecyclerView.Adapter<FavListAdaptor.RowHolder>{

    private List<Cat> catList;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    Cat catModel;
    CatDatabase db;
    CatDao catDao;
    SharedPreferences sharedPreferences;

    public FavListAdaptor(List<Cat> catList) {
        this.catList = catList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        sharedPreferences = view.getContext().getSharedPreferences("pref",MODE_PRIVATE);

        return new FavListAdaptor.RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(catList.get(position));
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView catName;
        ImageView catImage, fav;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Cat cat) {
            catName = itemView.findViewById(R.id.catName);
            catImage = itemView.findViewById(R.id.catImage);
            fav = itemView.findViewById(R.id.favOff);
            fav.setImageResource(R.drawable.fan_on);
            catName.setText(cat.name);

            if (cat.url != null) {
                Picasso.get().load(cat.url).resize(100, 80).centerCrop().into(catImage);
            } else {
                Picasso.get().load(R.drawable.unknowncat).into(catImage);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                    intent.putExtra("name",cat.name);
                    try {
                        intent.putExtra("image", cat.url);
                    } catch (Exception e) {
                    }
                    intent.putExtra("description", cat.description);
                    intent.putExtra("lifespan", cat.lifeSpan);
                    intent.putExtra("origin", cat.origin);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
