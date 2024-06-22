package com.example.katsanosergasia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private FirebaseHelper firebaseHelper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_a);

        searchView = findViewById(R.id.searchView);
        firebaseHelper = new FirebaseHelper();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "Searching for content: " + query);
                searchContent(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void searchContent(String query) {
        firebaseHelper.getContentByTitle(query, new FirebaseHelper.ContentDataListener() {
            @Override
            public void onContentDataReceived(Content content) {
                if (content != null) {
                    Log.d(TAG, "Content received: " + content.getTitle());
                    Intent intent = new Intent(MainActivity.this, ContentDetailsActivity.class);
                    intent.putExtra("content", content);
                    startActivity(intent);
                } else {
                    Log.d(TAG, "No content found");
                    Toast.makeText(MainActivity.this, "No content found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "Error: " + e.getMessage(), e);
                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
