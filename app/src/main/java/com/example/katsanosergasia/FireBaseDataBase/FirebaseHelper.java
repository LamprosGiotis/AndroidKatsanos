package com.example.katsanosergasia.FireBaseDataBase;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {
    private DatabaseReference databaseReference;

    public FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference("content");
    }

    public void getContent(String contentId, final ContentDataListener listener) {
        databaseReference.child(contentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Content content = dataSnapshot.getValue(Content.class);
                listener.onContentDataReceived(content);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.toException());
            }
        });
    }

    public interface ContentDataListener {
        void onContentDataReceived(Content content);
        void onError(Exception e);
    }
}
