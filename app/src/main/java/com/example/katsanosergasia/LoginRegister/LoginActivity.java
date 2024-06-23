package com.example.katsanosergasia.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.katsanosergasia.MainMenu.MainActivity;
import com.example.katsanosergasia.R;
import com.example.katsanosergasia.Users.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * @author Lampros Giotis
 * Σχετική περιγραφή:Αυτή η κλάση αναπαριστά ενα activity view μιας πλατφόρμας διασύνδεσης.Περιέχει όλα τα απαραίτητα συστατικά και μεθόδους
 * για την ορθή λειτουργία της όπως:Σύνδεση στο κεντρικό μενού,μετάβαση στην πλατφόρμα εγγραφής χρηστών,επικυροποίηση των στοιχείων κτλ.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;//Πεδία κειμένου για τον username του χρήστη.
    private EditText passwordField;//Ομοίως για το password.
    private boolean isDataLoaded = false;//Ένα είδους "flag" ια να ξέρουμε εάν τα data για τους χρήστες έχουν φορτωθεί σωστά.False γιατί θεωρούμε πως σε αυτό το στάδιο δεν έχουν αρχικοποιηθεί τα δεδομένα

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField = findViewById(R.id.UsernameField);//Σύνδεση με τις τιμές των fields(Τα βρίσκουμε μέσω του resources->layout->ID->ComponentID)
        passwordField = findViewById(R.id.PasswordField);//Ομοίως για password

        loadUsersFromFirebase();
    }
    /**
     * Αυτή η μέθοδος προσφέρει την διασύνδεση μεταξύ της πλατφόρμας σύνδεσης και της πλατφόρμας εγγραφής.
     * @param view Το νέο παράθυρο που θέλουμε να ανοίξουμε(Register)
     */
    public void openSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    /**
     * Αυτή η μέθοδος παρέχει την υπηρεσία διασύνδεσης των χρηστών στο κεντρικό μενού.Συλλέγουμε τα credential τους(username,password)
     * και αν είναι έγκυρα,όπως δηλαδή είχαν οριστεί στην πλατφόρμα εγγραφής,τότε η σύνδεση στο κεντρικό μενού είναι επιτυχής.
     * Διαφορετικά δίνονται κατάλληλα μηνύματα για μη επιτυχή συνδέσης:π.χ. Ο χρήστης δεν έχει πληκτρολογήσει σωστά τον κωδικό πρόσβασης του κτλ.
     * @param view
     */
    public void login(View view) {
        if (!isDataLoaded) {
            Toast.makeText(this, "Please wait, data is loading", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (isValidCredentials(username, password)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Αυτη η μέθοδος ελέχγει εάν τα credentials των χρηστών είναι ορθά δοσμένα
     * @param username το username του χρήστη
     * @param password το password του χρήστη
     * @return true εάν ο χρήστης υπάρχει(δεν είναι null) και ο κωδικός πρόσβασης του υπάρχει.
     */
    private boolean isValidCredentials(String username, String password) {
        HashMap<String, Users> usersList = Users.getUsersList();

        Users user = usersList.get(username);
        return user != null && user.getPassword().equals(password);
    }

    /**
     * Αυτή η μέθοδος φορτώνει τους χρήστης απο την firebase βάση δεδομένων στην λίστα με τους χρήστες
     */
    private void loadUsersFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");//Δημιουργούμε ενα αντικείμενο firebase και κάνουμε αναφορά στην κλάση των χρηστών
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Users> usersList = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users user = snapshot.getValue(Users.class);
                    if (user != null) {
                        usersList.put(user.getUsername(), user);
                    }
                }
                Users.setUsersList(usersList); // Τέλος αρχικοποιούμαι την λίστα με τους χρήστες απο την βάση
                isDataLoaded = true; // Φόρτωση δεδομένων ολοκληρώθηκε
            }

            /**
             * Αυτή η μέθοδος είναι υπεύθυνη για την αναφορά αποτυχίας φόρτωσης των δεδομένων.Δεν κάνει κάτι το ιδιαίτερο παρά μόνο
             * να εμφανίζει ένα αντίστοιχο μήνυμα toast
             * @param databaseError Το πρόβλημα που εμφανίστηκε
             */
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Failed to load data from Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
