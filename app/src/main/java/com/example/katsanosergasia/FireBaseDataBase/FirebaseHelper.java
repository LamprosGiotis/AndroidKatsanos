package com.example.katsanosergasia.FireBaseDataBase;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {
    private DatabaseReference databaseReference;
    private static final String TAG = "FirebaseHelper";

    public FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference("content");
    }

    public void getContentByTitle(String title, final ContentDataListener listener) {
        // Search in movies
        Query movieQuery = databaseReference.child("movies").orderByChild("title").equalTo(title);
        movieQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Content content = snapshot.getValue(Content.class);
                        content.setMovie(true); // Mark as movie
                        Log.d(TAG, "Movie found: " + content);
                        listener.onContentDataReceived(content);
                        return;
                    }
                } else {
                    // Search in series if no movie is found
                    Query seriesQuery = databaseReference.child("series").orderByChild("title").equalTo(title);
                    seriesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Content content = snapshot.getValue(Content.class);
                                    content.setMovie(false); // Mark as series
                                    Log.d(TAG, "Series found: " + content);
                                    listener.onContentDataReceived(content);
                                    return;
                                }
                            } else {
                                Log.d(TAG, "No content found for title: " + title);
                                listener.onContentDataReceived(null);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "Error fetching series by title", databaseError.toException());
                            listener.onError(databaseError.toException());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error fetching movie by title", databaseError.toException());
                listener.onError(databaseError.toException());
            }
        });
    }

    public interface ContentDataListener {
        void onContentDataReceived(Content content);
        void onError(Exception e);
    }
}
