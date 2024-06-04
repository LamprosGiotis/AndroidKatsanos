package com.example.katsanosergasia;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/**
 *  {@code @Author} Lampros Giotis
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
     * @param view To activity που βρισκόμαστε τώρα(Login)
     */
    public void openLogin(View view){
        //Ανοίγουμε κατευθείαν την login
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void clearAll(View view){
        if (usernameTextfield != null) {
            usernameTextfield.setText("");
        }
        if (passwordTextfield != null) {
            passwordTextfield.setText("");
        }
        if (confirmPasswordTextfield != null) {
            confirmPasswordTextfield.setText("");
        }
        if (usernameTextfieldLandscape != null) {
            usernameTextfieldLandscape.setText("");
        }
        if (passwordTextfieldlandscape != null) {
            passwordTextfieldlandscape.setText("");
        }
        if (confirmPasswordTextfieldlandscape != null) {
            confirmPasswordTextfieldlandscape.setText("");
        }
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