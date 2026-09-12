package com.example.abdulrahimfyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * MainActivity.java - Main Container Activity
 * This is the main container activity that hosts all fragments.
 * It serves as the central hub for the app with:
 * - A custom toolbar with the app title
 * - A FragmentContainerView that displays dynamic content
 * - A BottomNavigationView with 4 tabs: Zones, Plan, Log, Learn

 * Architecture: Single-activity, multi-fragment pattern.
 * Benefits:
 * - Modularity: Each tab is a separate Fragment
 * - Efficiency: Fragments are lighter than Activities
 * - Consistent UI: Toolbar and bottom nav remain constant

 * This is the main entry point after successful login.
 */

public class MainActivity extends AppCompatActivity {


    // Member Variables
    private BottomNavigationView bottomNavigationView;                 // Bottom navigation bar
    private FragmentManager fragmentManager;                           // Manages fragment transactions
    private SharedPreferences prefs;                                   // SharedPreferences for session data

    // Shared Preferences constants
    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    // Activity Lifecycle Methods

    /**
     * Called when the activity is first created.
     * Initialises the toolbar, bottom navigation, and loads the default fragment.
     *
     * @param savedInstanceState Saved instance state (if any)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   // Inflate the main layout


        // 1. Initialise SharedPreferences
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // 2. Set up Toolbar
        // Find the custom toolbar and set it as the support action bar.
        // The NoActionBar theme is used in AndroidManifest.xml to hide the default Action bar.

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hide the default title (we have a custom TextView in the toolbar)
        // This prevents duplicate titles from appearing
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 3. Initialise Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();

        // 4. Load the default fragment (Zones)
        // If this is the first time the activity is created (not a configuration change), load the ZonesFragment as the default tab.
        if (savedInstanceState == null) {
            loadFragment(new ZonesFragment());
        }

        // 5. Handle bottom navigation item selection
        // When a user taps a tab in the bottom navigation, switch to the corresponding fragment.
        // This listener dynamically replaces the fragment in the container.
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            // Determine which fragment to load based on the selected menu item ID
            if (itemId == R.id.navigation_zones) {
                selectedFragment = new ZonesFragment();       // Zone Calculator
            } else if (itemId == R.id.navigation_plan) {
                selectedFragment = new PlanFragment();        // Weekly Training Plan
            } else if (itemId == R.id.navigation_log) {
                selectedFragment = new LogFragment();         // Training Log
            } else if (itemId == R.id.navigation_learn) {
                selectedFragment = new LearnFragment();       // Educational Zone Guide
            }

            // If a fragment was selected, load it into the container
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;      // Indicate the selection was handled
            }
            return false;         // Selection not handled
        });
    }

    // Fragment Management Methods
    /**
     * Loads a fragment into the fragment container.
     * Uses a FragmentTransaction to replace the current fragment with the new one.

     * @param fragment The fragment to be displayed
     */

    private void loadFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)  // Replace the container's content
                .commit();     // Commit the transaction ( perform the replacement )
    }

    // Toolbar Menu Methods (Logout Feature)
    /**
     * Inflates the toolbar menu.
     * This adds the logout option to the toolbar's overflow menu.

     * @param menu The menu to inflate into @return true to indicate the menu is shown
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Logout Method
    /**
     * Logs the user out of the application.

     * Steps:
     * 1. Clear the login session flag in SharedPreferences (isLoggedIn = false)
     * 2. Create an Intent to navigate to LoginActivity
     * 3. Set flags to clear the activity stack (prevent returning via Back button)
     * 4. Start the LoginActivity and finish this activity

     * The FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK flags ensure:
     * - The LoginActivity becomes the root of the new task
     * - All existing activities (including MainActivity) are cleared
     * - Pressing "Back" from LoginActivity exits the app
     */
    private void logout() {
        // Clear the login session flag
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, false).apply();

        // Navigate to LoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        // Close MainActivity so it's removed from the back stack
        finish();
    }
}