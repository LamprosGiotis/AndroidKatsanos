package com.example.katsanosergasia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * @Author Lampros Giotis
 * Σχετική Περιγραφή:Αυτή η κλάση αναπαριστά ένα Login Αctivity μαζί με διάφορες μεθόδους για την ορθή υλοποίηση του.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LoginGUI), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Αυτή η μέθοδος διασυνδέει το Login με το Register Activity αναθέτοντας την λειτουργία της μεθόδου στο κουμπί "Sign Up"
     * @param view Το νέο activity που θα περάσουμε
     */
    public void openSignUp(View view){
        //Κατευθείαν καλούμε το νεο activity(Και ορίζουμε το OnClick attribute στο κουμπί Sign Up
        startActivity(new Intent(this,SignUpActivity.class));
    }

}