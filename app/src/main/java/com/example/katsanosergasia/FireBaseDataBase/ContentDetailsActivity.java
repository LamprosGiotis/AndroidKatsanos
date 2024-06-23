package com.example.katsanosergasia.FireBaseDataBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.katsanosergasia.FireBaseDataBase.Content;
import com.example.katsanosergasia.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContentDetailsActivity extends AppCompatActivity {
    private TextView titleTextView, starsTextView, dateTextView, runtimeTextView, seasonsTextView, episodesTextView, reviewTextView;
    private ImageView posterImageView;
    private Content content; // Store Content object to retain state

    private static final String TAG = "ContentDetailsActivity";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_LAYOUT_ID = "layoutId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the appropriate layout based on content type (movie or series)
        if (savedInstanceState != null) {
            int layoutId = savedInstanceState.getInt(KEY_LAYOUT_ID);
            setContentView(layoutId);
            initializeViews();
            content = (Content) savedInstanceState.getSerializable(KEY_CONTENT);
            if (content != null) {
                displayContentDetails(content);
            }
        } else {
            content = (Content) getIntent().getSerializableExtra("content");
            if (content != null) {
                int layoutId = content.isMovie() ? R.layout.activity_views_m : R.layout.activity_views_s;
                setContentView(layoutId);
                initializeViews();
                displayContentDetails(content);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current layout ID and content object
        outState.putInt(KEY_LAYOUT_ID, getLayoutId());
        outState.putSerializable(KEY_CONTENT, content);
    }

    private int getLayoutId() {
        // Determine which layout is currently set
        return content.isMovie() ? R.layout.activity_views_m : R.layout.activity_views_s;
    }

    private void initializeViews() {
        // Initialize views from the current layout
        titleTextView = findViewById(R.id.titleTextView);
        starsTextView = findViewById(R.id.starsTextView);
        dateTextView = findViewById(R.id.dateTextView);
        runtimeTextView = findViewById(R.id.runtimeTextView);
        seasonsTextView = findViewById(R.id.seasonTextView);
        episodesTextView = findViewById(R.id.episodeTextView);
        reviewTextView = findViewById(R.id.reviewTextView);
        posterImageView = findViewById(R.id.posterImageView);
    }

    private void displayContentDetails(Content content) {
        // Set text and image
        if (titleTextView != null) {
            titleTextView.setText("Title: " + content.getTitle());
        }
        if (starsTextView != null) {
            starsTextView.setText("Stars: " + content.getStars());
        }
        if (dateTextView != null) {
            dateTextView.setText("Date: " + content.getDate());
        }
        if (reviewTextView != null) {
            reviewTextView.setText("Review: " + content.getReview());
        }

        if (content.isMovie()) {
            if (runtimeTextView != null) {
                runtimeTextView.setText("Runtime: " + content.getRuntime());
            }
            if (seasonsTextView != null) {
                seasonsTextView.setText(""); // Hide for movie
            }
            if (episodesTextView != null) {
                episodesTextView.setText(""); // Hide for movie
            }
        } else {
            if (seasonsTextView != null) {
                seasonsTextView.setText("Seasons: " + String.valueOf(content.getSeasons()));
            }
            if (episodesTextView != null) {
                episodesTextView.setText("Episodes: " + String.valueOf(content.getEpisodes()));
            }
            if (runtimeTextView != null) {
                runtimeTextView.setText("Runtime: " + String.valueOf(content.getRuntime()));
            }
        }

        // Load image using Picasso
        if (posterImageView != null && content.getImageUrl() != null && !content.getImageUrl().isEmpty()) {
            Picasso.get()
                    .load(content.getImageUrl())
                    .into(posterImageView);
        }
    }

}