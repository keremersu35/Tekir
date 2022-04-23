package com.example.tekirapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tekirapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.RowHolder> {

    private ArrayList<CatListModel> catList;
    private ArrayList<CatListModel> catListFull;

    public HomepageAdapter(ArrayList<CatListModel> catList) {
        this.catList = catList;
        catListFull = new ArrayList<>(catList);

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
        ImageView catImage;

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
                    intent.putExtra("searchedProfile", catName.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
/*
    public Filter getFilter() {
            return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CatListModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filteredList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CatListModel item : catListFull) {
                    if (item.name.toString().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            catList.clear();
            catList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
*/
    }
