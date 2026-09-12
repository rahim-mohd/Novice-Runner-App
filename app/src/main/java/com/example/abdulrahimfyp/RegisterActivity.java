package com.example.abdulrahimfyp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * RegisterActivity.java - User Registration Screen
 * This activity handles new user registration.

 * Features:
 * - Users enter their full name, email, password, and confirm password
 * - Validation checks for empty fields, password length, and password match
 * - Prevents duplicate email registration
 * - On successful registration, credentials are saved to SharedPreferences
 * - User is returned to the Login screen

 * Flow:
 * 1. User fills in registration form
 * 2. Validation checks are performed
 * 3. If valid, credentials are saved to SharedPreferences
 * 4. Success message is shown and activity closes (returns to Login)
 * 5. User can then log in with their new credentials
 */

public class RegisterActivity extends AppCompatActivity {

    // View References (UI Components)


    private EditText etFullName;                 // Full name input field
    private EditText etEmail;                    // Email input field
    private EditText etPassword;                 // Password input field
    private EditText etConfirmPassword;          // Confirm password input field
    private Button btnRegister;                  // Register button
    private ImageButton btnBack;                 // Back button (Returns to login page)
    private TextView tvError;                    // Displays error messages


    // SharedPreferences Constants

    private SharedPreferences prefs;                           // SharedPreferences instance for data storage
    private static final String PREFS_NAME = "UserPrefs";      // Name of the preferences file
    private static final String KEY_EMAIL = "userEmail";       // Key for storing email
    private static final String KEY_PASSWORD = "userPassword"; // Key for storing password
    private static final String KEY_FULL_NAME = "userFullName";  // Key for storing full name


    // Activity Lifecycle Methods

    /**
     * Called when the activity is first created.
     * Initialises views, SharedPreferences, and sets up click listeners.
     *
     * @param savedInstanceState Saved instance state (if any)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);        // Inflate the registration layout

        // 1. Initialize views
        // Find each UI component by its ID and store in the corresponding variable.
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);
        tvError = findViewById(R.id.tvError);

        // 2. Initialize SharedPreferences
        // Get the SharedPreferences instance for storing user data.
        // MODE_PRIVATE means this data is only accessible by this app.
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // 3. Set up Register button click listener
        btnRegister.setOnClickListener(v -> {
            // Get user input and trim whitespace
            String fullName = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Validation Checks (Edge Cases)

            // Edge Case 1: Empty Full Name
            if (fullName.isEmpty()) {
                tvError.setText("Please enter your full name");
                return;
            }

            // Edge Case 2: Empty email
            if (email.isEmpty()) {
                tvError.setText("Please enter your email address");
                return;
            }

            // Edge Case 3: Empty Password
            if (password.isEmpty()) {
                tvError.setText("Please enter a password");
                return;
            }

            // Edge Case 4: Password minimum length
            // Ensures passwords are at least 6 characters
            if (password.length() < 6) {
                tvError.setText("Password must be at least 6 characters");
                return;
            }

            // Edge Case 5: Password confirmation mismatch
            // Ensures users typed the same password twice correctly
            if (!password.equals(confirmPassword)) {
                tvError.setText("Passwords do not match");
                return;   // Stop execution and let user correct the input
            }

            // Edge Case 6: Duplicate Email
            // Check if email already exists in SharedPreferences
            String storedEmail = prefs.getString(KEY_EMAIL, "");
            if (email.equals(storedEmail)) {
                tvError.setText("This email is already registered. Please login.");
                return;   // Stop execution and let user navigate to login
            }

            // 4. Save user credentials
            // All validation passed --> Save the new user's credentials to SharedPreferences
            prefs.edit()
                    .putString(KEY_FULL_NAME, fullName)     // Store full name
                    .putString(KEY_EMAIL, email)            // Store email
                    .putString(KEY_PASSWORD, password)      // Store password
                    .apply();                               // apply() saves asynchronously


            // Clear any previous error message
            tvError.setText("");

            // Show success message
            Toast.makeText(this, "Registration successful! Please login.", Toast.LENGTH_LONG).show();

            // Close this activity and return to LoginActivity
            finish();
        });

        // 5. Set up Back button click listener
        // When the user clicks the back arrow icon, close this activity and return to the login screen
        btnBack.setOnClickListener(v -> finish());
    }
}