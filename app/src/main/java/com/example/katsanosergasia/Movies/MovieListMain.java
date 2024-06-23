package com.example.katsanosergasia.Movies;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.katsanosergasia.R;

import java.util.List;

public class MovieListMain extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<MovieList.ViewHolder> adapter;
    private List<Integer> imageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_main);

        recyclerView = findViewById(R.id.recycler_view);

        //Set the layout of the items in the RecyclerView
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Set my Adapter for the RecyclerView
        adapter = new MovieList(this, imageList);
        recyclerView.setAdapter(adapter);
    }



}