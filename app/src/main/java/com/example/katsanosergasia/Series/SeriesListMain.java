package com.example.katsanosergasia.Series;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.katsanosergasia.R;

public class SeriesListMain extends AppCompatActivity {
    RecyclerView recyclerViewSeries;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<SeriesList.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list_main);

        recyclerViewSeries = findViewById(R.id.recycler_view_series);

        //Set the layout of the items in the RecyclerView
        layoutManager = new LinearLayoutManager(this);
        recyclerViewSeries.setLayoutManager(layoutManager);

        //Set my Adapter for the RecyclerView
        adapter = new SeriesList();
        recyclerViewSeries.setAdapter(adapter);
    }
}