package com.example.abdulrahimfyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * LoginActivity.java - User Authentication (Login Screen)
 * This activity handles user login authentication.

 * Features:
 * - Users enter their email/username and password
 * - Credentials are validated against stored SharedPreferences data
 * - Successful login saves a session flag and navigates to MainActivity
 * - Users can navigate to the registration screen if they don't have an account

 * Flow:
 * 1. User opens the app → LoginActivity starts
 * 2. Check if user is already logged in (isLoggedIn flag)
 * 3. If logged in → Skip to MainActivity
 * 4. If not logged in → Show login form
 * 5. User enters credentials and clicks Login
 * 6. Credentials validated → On success, save session and navigate to MainActivity
 */

public class LoginActivity extends AppCompatActivity {

    // View References (UI Components)

    private EditText etEmail;        // Email/Username input fields
    private EditText etPassword;     // Password input fields
    private Button btnLogin;         // Login button
    private TextView tvRegisterLink; // Link to registration screen
    private TextView tvError;         // Displays error messages



    // SharedPreferences Constants
    private SharedPreferences prefs;   // SharedPreferences instance for data storage
    private static final String PREFS_NAME = "UserPrefs";  // Name of preferences file
    private static final String KEY_EMAIL = "userEmail";  //Key for stored email
    private static final String KEY_PASSWORD = "userPassword";  // Key for stored password
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";  // Key for login session flag


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
        setContentView(R.layout.activity_login);  //Inflate the login layout

        // 1. Initialize views
        // Find each UI component by its ID and store in the corresponding variable.
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);
        tvError = findViewById(R.id.tvError);

        // 2. Initialize SharedPreferences
        // Get the SharedPreferences instance for storing user data.
        // MODE_PRIVATE means this data is only accessible by this app.
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // 3. Check if user is already logged in
        // If user is already logged in (isLoggedIn = true), skip the login screen and go directly to MainActivity.
        if (prefs.getBoolean(KEY_IS_LOGGED_IN, false)) {
            navigateToMain();      // Navigate to MainActivity
            return;                // Stop further execution in onCreate
        }

        // 4. Set up login Button Click Listener
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Edge Case 1: Empty Email
            if (email.isEmpty()) {
                tvError.setText("Please enter your email");
                return;     // Stop execution and let user correct the input
            }

            // Edge Case 2: Empty Password
            if (password.isEmpty()) {
                tvError.setText("Please enter your password");
                return;        // Stop execution and let user correct the input
            }

            // Validate Credentials
            if (validateLogin(email, password)) {
                // Credentials are correct
                tvError.setText("");     // Clear any previous error message

                // Save login session flag to SharedPreferences
                prefs.edit()
                        .putBoolean(KEY_IS_LOGGED_IN, true)
                        .apply();   // apply() saves asynchronously

                // Show success message
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

                // Navigate to MainActivity
                navigateToMain();
            } else {
                // Credentials are incorrect:
                tvError.setText("Invalid email or password. Please try again.");
            }
        });

        // Register link click
        tvRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    //Helper Methods

    /**
     * Validates the user's login credentials against stored SharedPreferences data.

     * This method retrieves the stored email and password from SharedPreferences and compares them with the provided credentials.
     * @param email The email entered by the user
     * @param password  The password entered by the user
     * @return    True if credentials match, false otherwise
     */

    private boolean validateLogin(String email, String password) {
        // Retrieve stored credentials from SharedPreferences
        // If no data exists, default to empty strings ("")
        String storedEmail = prefs.getString(KEY_EMAIL, "");
        String storedPassword = prefs.getString(KEY_PASSWORD, "");

        // Compare provided credentials with stored credentials
        return email.equals(storedEmail) && password.equals(storedPassword);
    }

    /**
     * Navigates to MainActivity and clears the activity stack.

     *  This method is called when:
     *  1. The user successfully logs in
     *  2. The user is already logged in (session check in onCreate)

     *  The FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK flags ensure that:
     *   - The LoginActivity is removed from the back stack
     *   - Pressing "Back" from the MainActivity does not return to LoginActivity
     */
    private void navigateToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();  // Close LoginActivity so it cannot be accessed via Back button
    }
}