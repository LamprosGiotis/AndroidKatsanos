
package com.example.katsanosergasia.FireBaseDataBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.katsanosergasia.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContentDetailsActivity extends AppCompatActivity {
    private TextView titleTextView, starsTextView, dateTextView, runtimeTextView, seasonsTextView, episodesTextView, reviewTextView;
    private ImageView posterImageView;

    private static final String TAG = "ContentDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Content content = (Content) getIntent().getSerializableExtra("content");
        if (content != null) {
            if (content.isMovie()) {
                setContentView(R.layout.activity_views_m);
            } else {
                setContentView(R.layout.activity_views_s);
            }
            initializeViews();
            displayContentDetails(content);
        }
    }

    private void initializeViews() {
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
        titleTextView.setText(content.getTitle());
        starsTextView.setText(content.getStars());
        dateTextView.setText(content.getDate());
        reviewTextView.setText(content.getReview());

        if (content.isMovie()) {
            runtimeTextView.setText(content.getRuntime());
            seasonsTextView.setText(""); // Hide for movie
            episodesTextView.setText(""); // Hide for movie
        } else {
            runtimeTextView.setText(""); // Hide for series
            seasonsTextView.setText(String.valueOf(content.getSeasons()));
            episodesTextView.setText(String.valueOf(content.getEpisodes()));
        }

        if (content.getImageUrl() != null && !content.getImageUrl().isEmpty()) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Bitmap> future = executor.submit(() -> {
                try {
                    InputStream inputStream = new URL(content.getImageUrl()).openStream();
                    return BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            });

            try {
                Bitmap bitmap = future.get();
                if (bitmap != null) {
                    posterImageView.setImageBitmap(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            executor.shutdown();
        }
    }
}
