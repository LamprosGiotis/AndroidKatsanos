package com.example.katsanosergasia.LoginRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.katsanosergasia.R;
import com.example.katsanosergasia.Users.Users;

public class SignUpActivity extends AppCompatActivity {

    private TextView usernameTextfield;//Σύνδεση του textfield
    private TextView passwordTextfield;//Ομοίως και για το password
    private TextView confirmPasswordTextfield;//Για το confirm password
    private ProgressDialog progressDialog;//Ένα αντικείμενο (για αισθητικούς λόγους)που κάνει display το progress

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameTextfield = findViewById(R.id.UsernameTextfield);
        passwordTextfield = findViewById(R.id.PasswordTextfield);
        confirmPasswordTextfield = findViewById(R.id.ConfirmPasswordTextfield);
        progressDialog = new ProgressDialog(this);
    }

    /**
     * Αυτή η μέθοδος προσφέρει την διασύνδεση μεταξύ του κεντρικού μενού,και της πλατφόρμας διασύνδεσης
     *
     * @param view Το νέο παράθυρο που θέλουμε να ανοίξουμε(Login)
     */
    public void openLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));//Ανοίγουμε κατευθείαν την Login
    }

    /**
     * Αυτή η μέθοδος καθαρίζει τις τιμές από όλα τα fields και αφήνει ένα μικρό μήνυμα
     *
     * @param view το View που είμαστε
     */
    public void clearAll(View view) {
        clearField(usernameTextfield);
        clearField(passwordTextfield);
        clearField(confirmPasswordTextfield);
        Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();
    }

    /**
     * Αυτή η μέθοδος ελέχγει εάν το editfield είναι κενό(Διαφορετικά αν δεν είναι δεν χρήζει εκαθάριση)
     *
     * @param textView
     */
    private void clearField(TextView textView) {
        if (textView != null) {//Ελέχγουμε την κατάσταση του field πριν προβόυμε σε clear
            textView.setText("");
        }
    }

    /**
     * Αυτή η μέθοδος προσσφέρει την υπηρεσία εγγραφής των χρηστών στην firebase Data base.
     * Συλλέγει όλες τις τιμές απο τα fields(credentials των χρηστων),και στην συνέχεια εάν έχουν ικανοποιηθεί τα κριτήρια,τότε η εγγραφή είναι επιτυχής.
     * Διαφορετικά θα εμφανίσει ένα μήνυμα βοήθειας προς τον χρήστη:π.χ. Ο χρήστης έχει διαφορετικά password στα passwordfield και confirmpasswordfield.
     *
     * @param view
     */
    public void signUp(View view) {
        String username = usernameTextfield.getText().toString().trim();//Παίρνουμε τις τιμες απο τα πεδία και τριμάρουμε για τυχόν κενά(αριστερά ή δεξία)
        String password = passwordTextfield.getText().toString().trim();//Ομόιως και για τα άλλα
        String confirmPassword = confirmPasswordTextfield.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {//Ελέχγουμε εάν κάποιο από τα πεδία δεν έχει συμπληρωθεί
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();//Διαφορετικά εμφανίζουμε ένα μήνυμα ενημέρωσης
            return;
        }

        if (!password.equals(confirmPassword)) {//Ελέχγουμε εάν οι κωδικοί στα passwordfield και confirmpasswordfield είναι ίδιοι
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();//Διαφορετικά εμφανίζουμε ένα μήνυμα ενημέρωσης
            return;
        }

        Users.loadUsersFromFirebase(); // Φορτώνουμε τους χρήστες(Από την βάση)

        if (Users.getUser(username) != null) {//Ελέχγουμε εάν κάποιος άλλος χρήστης έχει δώσει
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            return;//
        }

        Users newUser = new Users(username, password);//Δημιουργούμε ένα αντικείμενο χρήστη

        progressDialog.setMessage("Registering user...");//Εμαφνίζουμε την πρόοδο
        progressDialog.show();


        Users.addUser(newUser); // Προσθέτουμε τον χρήστη στην firebase

        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();//Αφήνουμε ένα μήνυμα ενημέρωσης

        clearAll(null); // Καθαρίζουμε όλα τα fields αφού γίνει η εγγραφή
        startActivity(new Intent(this, LoginActivity.class));//Ανοίγουμε την login
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", usernameTextfield.getText().toString());
        outState.putString("password", passwordTextfield.getText().toString());
        outState.putString("confirmPassword", confirmPasswordTextfield.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            usernameTextfield.setText(savedInstanceState.getString("username"));
            passwordTextfield.setText(savedInstanceState.getString("password"));
            confirmPasswordTextfield.setText(savedInstanceState.getString("confirmPassword"));
        }
    }

}
