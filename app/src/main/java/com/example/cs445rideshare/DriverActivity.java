package com.example.cs445rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DriverActivity extends AppCompatActivity {

    private Button buttonChangeInfo;
    private Button buttonViewReviews;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        // Initialize buttons
        buttonChangeInfo = findViewById(R.id.buttonChangeInfo);
        buttonViewReviews = findViewById(R.id.buttonViewReviews);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Set click listeners for buttons
        buttonChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click to change information
                Toast.makeText(DriverActivity.this, "Change Information Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        buttonViewReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click to view reviews
                Toast.makeText(DriverActivity.this, "View Reviews Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout button click
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DriverActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
