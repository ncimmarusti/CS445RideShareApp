package com.example.cs445rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextPhoneNumber;
    private Switch switchAccountType;
    private Button buttonRegister;
    private TextView textViewReturnToLogin;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);

        editTextPassword = findViewById(R.id.editTextPassword);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        PhoneNumberFormatting.addPhoneNumberFormatting(editTextPhoneNumber);

        buttonRegister = findViewById(R.id.buttonRegister);

        switchAccountType = findViewById(R.id.switchAccountType); // Initialize switchAccountType

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        textViewReturnToLogin = findViewById(R.id.textViewReturnToLogin);

        textViewReturnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToLogin(v);
            }
        });
    }

    public void returnToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the RegisterActivity to prevent returning to it using the back button
    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        final boolean isDriver = switchAccountType.isChecked(); // Check if the user is a driver

        // Validate email format
        if (!isValidEmail(email)) {
            editTextEmail.setError("Enter a valid email address");
            return;
        }

        // Validate password length and special character
        if (!isValidPassword(password)) {
            editTextPassword.setError("Password must be at least 8 characters long and contain a special character");
            return;
        }

        // Validate phone number
        if (!isValidPhoneNumber(phoneNumber)) {
            editTextPhoneNumber.setError("Enter a valid phone number");
            return;
        }

        // Check if any of the fields are empty
        if (email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Proceed with user registration
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User registration successful
                            String userId = mAuth.getCurrentUser().getUid();

                            // Store additional user information in Firestore
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("email", email);
                            userData.put("phoneNumber", phoneNumber);
                            userData.put("isDriver", isDriver);
                            FirebaseFirestore.getInstance()
                                    .collection("Users")
                                    .document(userId)
                                    .set(userData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Firestore data addition successful
                                                redirectUser(isDriver);
                                            } else {
                                                // Handle Firestore write operation failure
                                                if (task.getException() instanceof FirebaseFirestoreException && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.NOT_FOUND) {
                                                    FirebaseFirestore.getInstance().collection("Users").document().set(new HashMap<>());
                                                    registerUser();
                                                } else {
                                                    // Display error message to the user
                                                    Toast.makeText(RegisterActivity.this, "Failed to add user data", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    });
                        } else {
                            // Registration failed
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void redirectUser(boolean isDriver) {
        try {
            // Redirect to appropriate activity based on account type
            if (isDriver) {
                startActivity(new Intent(RegisterActivity.this, DriverActivity.class));
            } else {
                startActivity(new Intent(RegisterActivity.this, UserActivity.class));
            }
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(RegisterActivity.this, "Error redirecting to activity", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to validate email format
    private boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Method to validate password length and special character
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String pattern = "\\d{3}-\\d{3}-\\d{4}"; // Example: 123-456-7890

        // Check if the provided phone number matches the pattern
        return phoneNumber.matches(pattern);
    }
}

