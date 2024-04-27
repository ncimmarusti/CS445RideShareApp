package com.example.cs445rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * This class will implement the user interface and will handle the button click logic for the homepage.
 */

public class UserActivity extends AppCompatActivity {

    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        buttonLogout = findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout button click
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DriverActivity.this, LoginActivity.class));
                finish(); // Optional: Finish the current activity to prevent the user from going back
            }
        });
    }
}