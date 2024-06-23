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
    private final DatabaseReference databaseReference;
    private static final String TAG = "FirebaseHelper";

    public FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference("content");
    }

    public void getContentByTitle(String title, final ContentDataListener listener) {
        // Αναζήτηση σε ταινίες
        Query movieQuery = databaseReference.child("movies").orderByChild("title").equalTo(title);
        movieQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Content content = snapshot.getValue(Content.class);
                        assert content != null;
                        content.setMovie(true); // Σήμανση ως ταινία
                        Log.d(TAG, "Βρέθηκε ταινία: " + content);
                        listener.onContentDataReceived(content);
                        return;
                    }
                } else {
                    // Αναζήτηση σε σειρές αν δεν βρεθεί ταινία
                    Query seriesQuery = databaseReference.child("series").orderByChild("title").equalTo(title);
                    seriesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Content content = snapshot.getValue(Content.class);
                                    assert content != null;
                                    content.setMovie(false); // Σήμανση ως σειρά
                                    Log.d(TAG, "Βρέθηκε σειρά: " + content);
                                    listener.onContentDataReceived(content);
                                    return;
                                }
                            } else {
                                Log.d(TAG, "Δεν βρέθηκε περιεχόμενο με τίτλο: " + title);
                                listener.onContentDataReceived(null);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "Σφάλμα κατά την αναζήτηση σειράς με τίτλο", databaseError.toException());
                            listener.onError(databaseError.toException());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Σφάλμα κατά την αναζήτηση ταινίας με τίτλο", databaseError.toException());
                listener.onError(databaseError.toException());
            }
        });
    }

    public interface ContentDataListener {
        void onContentDataReceived(Content content);
        void onError(Exception e);
    }
}
