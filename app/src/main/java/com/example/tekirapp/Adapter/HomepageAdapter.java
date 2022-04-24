package com.example.tekirapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tekirapp.Model.CatListModel;
import com.example.tekirapp.R;
import com.example.tekirapp.View.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.RowHolder> {

    private ArrayList<CatListModel> catList;

    public HomepageAdapter(ArrayList<CatListModel> catList) {
        this.catList = catList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new RowHolder(view);
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
        ImageView catImage,star;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CatListModel catListModel) {
            catName = itemView.findViewById(R.id.catName);
            catImage = itemView.findViewById(R.id.catImage);
            catName.setText(catListModel.name);

            if (catListModel.image != null) {
                Picasso.get().load(catListModel.image.getUrl()).resize(100, 80).into(catImage);
            } else {
                Picasso.get().load(R.drawable.unknowncat).into(catImage);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                    intent.putExtra("name", catListModel.name);
                    try {
                        intent.putExtra("image", catListModel.image.getUrl());
                    } catch (Exception e) {
                    }
                    intent.putExtra("description", catListModel.description.toString());
                    intent.putExtra("lifespan", catListModel.lifeSpan.toString());
                    intent.putExtra("origin", catListModel.origin.toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
