package com.example.tekirapp.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tekirapp.R;
import com.example.tekirapp.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    ImageView catImageDetail;
    TextView catNameDetail, catDescriptionDetail, catOriginDetail, catLifeSpanDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

       catImageDetail = findViewById(R.id.catImageDetail);
       catNameDetail = findViewById(R.id.catNameDetail);
       catDescriptionDetail = findViewById(R.id.catDescriptionDetail);
       catOriginDetail = findViewById(R.id.catOriginDetail);
       catLifeSpanDetail = findViewById(R.id.catLifeSpanDetail);

       getData();
    }

    public void getData(){
        catNameDetail.setText("Name: "+getIntent().getStringExtra("name"));
        catDescriptionDetail.setText("Description: "+getIntent().getStringExtra("description"));
        catOriginDetail.setText("Origin: "+getIntent().getStringExtra("origin"));
        catLifeSpanDetail.setText("Life Span: "+ getIntent().getStringExtra("lifespan"));
        if (getIntent().getStringExtra("image") != null) {
            System.out.println(getIntent().getStringExtra("image"));
            Picasso.get().load(getIntent().getStringExtra("image")).resize(100, 80).into(catImageDetail);
        } else {
            Picasso.get().load(R.drawable.unknowncat).into(catImageDetail);
        }
    }
}