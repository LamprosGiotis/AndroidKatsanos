package com.example.katsanosergasia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/**
 *  @Author Lampros Giotis
 * Σχετική Περιγραφή:Αυτή η κλάση αναπαριστά ένα Register Αctivity μαζί με διάφορες μεθόδους για την ορθή υλοποίηση του.
 */
public class SignUpActivity extends AppCompatActivity {

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
    }

    /**
     * Παρόμοια με την OpenLogin μέθοδο.Διασυνδέει την register με την login.
     * @param view
     */
    public void openLogin(View view){
        //Ανοίγουμε κατευθείαν την login
        startActivity(new Intent(this, LoginActivity.class));
    }
}