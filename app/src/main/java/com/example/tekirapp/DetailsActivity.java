package com.example.tekirapp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tekirapp.databinding.ActivityDetailsBinding;


import android.os.Bundle;
import android.view.View;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

}