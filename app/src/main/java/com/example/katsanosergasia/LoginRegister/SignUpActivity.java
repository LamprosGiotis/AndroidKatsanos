package com.example.katsanosergasia.LoginRegister;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.katsanosergasia.LoginRegister.LoginActivity;
import com.example.katsanosergasia.R;

/**
 * {@code @Author} Lampros Giotis
 * Σχετική Περιγραφή:Αυτή η κλάση αναπαριστά ένα Register Αctivity μαζί με διάφορες μεθόδους για την ορθή υλοποίηση του.
 */
public class SignUpActivity extends AppCompatActivity {

    TextView usernameTextfield;
    TextView passwordTextfield;
    TextView confirmPasswordTextfield;
    TextView usernameTextfieldLandscape;
    TextView passwordTextfieldlandscape;
    TextView confirmPasswordTextfieldlandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameTextfield = findViewById(R.id.UsernameTextfield);
        passwordTextfield = findViewById(R.id.PasswordTextfield);
        confirmPasswordTextfield = findViewById(R.id.ConfirmPasswordTextfield);
        usernameTextfieldLandscape = findViewById(R.id.UsernameTextfieldLandscape);
        passwordTextfieldlandscape = findViewById(R.id.PasswordtextfieldLandscape);
        confirmPasswordTextfieldlandscape = findViewById(R.id.ConfirmTextfieldLandscape);
    }

    /**
     * Παρόμοια με την OpenRegister μέθοδο.Διασυνδέει την register με την login.
     *
     * @param view To activity που βρισκόμαστε τώρα(Login)
     */
    public void openLogin(View view) {
        //Ανοίγουμε κατευθείαν την login
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     * Αυτή η μέθοδος κάνει clear όλα τα fields παρέχοντας στον χρήστη μια μικρή βοήθεια όταν θέλει να καθαρίσει όλα τα πεδία,
     * αντί να το κάνει χειροκίνητα σε μορφή "ένα-ένα"
     * @param view Το activity που βρίσκεται το Clear All button.
     */
    public void clearAll(View view) {
        if (areAllFieldsEmpty()) { //Ελέχγουμε εάν όλα τα πεδία είναι άδεια με την χρήση της areAllFieldsEmpty
            Toast.makeText(this, "Fields are already empty", Toast.LENGTH_SHORT).show();//Αφήνουμε ένα μικρό μήνυμα ενημέρωσης
        } else {
            clearField(usernameTextfield);//Αφαιρούμε την τρέχουσα τιμή του field,ομοίως και για τα υπόλοιπα fields,ασχέτως το orientation
            clearField(passwordTextfield);
            clearField(confirmPasswordTextfield);
            clearField(usernameTextfieldLandscape);
            clearField(passwordTextfieldlandscape);
            clearField(confirmPasswordTextfieldlandscape);
            Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();//Επίσης αφήνουμε ακόμη ένα μήνυμα ενημέρωσης
        }
    }

    /**
     *  Αυτή η μέθοδος "καθαρίζει" την τρέχουσα τιμή του field
     * @param textView το επιλεγόμενο field που πρόκυται η τιμή του να γίνει clear
     */
    private void clearField(TextView textView) {
        //Αρχικά ελέχγουμε εάν ο χρήστης έχει δώσει κάποιο input
        if (textView != null) { //Εάν έχει,τότε πάει να πει ότι το field δεν είναι null,άρα η συνθήκη είναι έγκυρη
            textView.setText("");//Θέτουμε την τιμή του σε " "
        }
    }

    /**
     *  Αυτή η μέθοδος ελέχγει εάν όλα τα fields είναι άδεια
     * @return Επιστρέφει true Η false ανάλογα εάν ισχύει η συνθήκη της isEmpty.
     */
    private boolean areAllFieldsEmpty() {
        return isEmpty(usernameTextfield) &&
                isEmpty(passwordTextfield) &&
                isEmpty(confirmPasswordTextfield) &&
                isEmpty(usernameTextfieldLandscape) &&
                isEmpty(passwordTextfieldlandscape) &&
                isEmpty(confirmPasswordTextfieldlandscape);
    }
    /**
     * Αυτή η μέθοδος ελέχγει εάν ένα field είναι άδειο,αθροιστικά θα το κάνει για όλα απο την areAllFieldsEmpty
     * @param textView που θέλουμε να εξετάσουμε
     * @return true εαν είναι null,διαφορετικά false
     */
    private boolean isEmpty(TextView textView) {
        return textView == null || textView.getText().toString().isEmpty();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}