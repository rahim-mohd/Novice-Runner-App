package com.example.abdulrahimfyp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * LogFragment.java - Training Log (Log Tab)
 * This fragment allows users to manually record their running activities.

 * Features:
 * - Multi-line text input for entering run details
 * - "Save Entry" button to store the log with a date/time stamp
 * - Scrollable display of all previously saved logs
 * - Logs are stored locally using SharedPreferences

 * Data Persistence:
 * - Uses SharedPreferences to store logs as a single string
 * - Each entry is formatted as: "dd/MM/yyyy HH:mm - [user entry]"
 * - New entries are added at the top for easy viewing

 * This feature addresses the need for progress tracking identified
 * in the qualitative research.
 */

public class LogFragment extends Fragment {

    // View References


    private EditText etLogEntry;     // Input field for run details
    private Button btnSaveLog;       // Save entry button
    private TextView tvSavedLogs;    // Displays all saved logs


    // SharedPreferences Constants
    private SharedPreferences prefs;                      // SharedPreferences instance
    private static final String PREFS_NAME = "RunLog";    // Name of the preferences file
    private static final String KEY_LOGS = "logs";        // Key storing log entries


    // Fragment Lifecycle Methods

    /**
     * Called when the fragment is first created.
     * Initialises views, SharedPreferences, and sets up the save button.

     * @param inflater           The LayoutInflater used to inflate the view
     * @param container          The parent ViewGroup that this fragment is attached to
     * @param savedInstanceState Saved instance state (if any)
     * @return The inflated View for this fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_log layout
        View view = inflater.inflate(R.layout.fragment_log, container, false);


        // 1. Initialise Vies
        etLogEntry = view.findViewById(R.id.etLogEntry);
        btnSaveLog = view.findViewById(R.id.btnSaveLog);
        tvSavedLogs = view.findViewById(R.id.tvSavedLogs);


        // 2. Initialise SharedPreferences
        // Get the SharedPreferences instance for storing run logs.
        // MODE_PRIVATE means this data is only accessible by this app.
        prefs = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);


        // 3. Display Existing Lgos
        // Load and display any previously saved logs when the fragment opens.
        displayLogs();


        // 4. Set up the save button click listener
        btnSaveLog.setOnClickListener(v -> {
            // Get user input and trim whitespace
            String logEntry = etLogEntry.getText().toString().trim();


            // Edge Case: Empt input
            // Check if the user left the log entry field empty
            if (logEntry.isEmpty()) {
                Toast.makeText(getContext(), "Please enter your run details", Toast.LENGTH_SHORT).show();
                return;    // Stop execution and let user correct the input
            }

            // Save the log
            // Add date/time stamp and save to SharedPreferences
            saveLog(logEntry);

            // Clear input field
            // Clear the input filed after saving for a clean user experience
            etLogEntry.setText("");

            // Refresh Display
            // Update the display to show the newly saved log
            displayLogs();

            // Show Success Message
            Toast.makeText(getContext(), "Log saved successfully!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    // Data Persistence Methods

    /**
     * Saves a log entry with the current date and time.

     * Steps:
     * 1. Get the current date and time in "dd/MM/yyyy HH:mm" format
     * 2. Format the new entry as: "date - user entry"
     * 3. Retrieve existing logs from SharedPreferences
     * 4. Prepend the new entry to the existing logs (newest at top)
     * 5. Save the updated string back to SharedPreferences

     * Using "newest at top" makes it easier for users to see their most recent runs.

     * @param entry The user's log entry text
     */
    private void saveLog(String entry) {
        // 1. Get current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String dateTime = sdf.format(new Date());

        // 2. Format the new entry
        String newEntry = dateTime + " - " + entry;

        // 3. Get existing logs (default to empty string if none exist)
        String existingLogs = prefs.getString(KEY_LOGS, "");

        // 4. Prepend new entry (newest at top for easier viewing)
        String updatedLogs = newEntry + "\n\n" + existingLogs;

        // 5. Save to SharedPreferences
        // apply() saves asynchronously without blocking the UI thread
        prefs.edit().putString(KEY_LOGS, updatedLogs).apply();
    }

    /**
     * Displays all saved logs in the TextView.
     * If no logs exist, shows a placeholder message.
     */
    private void displayLogs() {
        // Retrieve logs from SharedPreferences (default to empty string)
        String logs = prefs.getString(KEY_LOGS, "");

        if (logs.isEmpty()) {
            // No logs saved yet - show placeholder message
            tvSavedLogs.setText("No logs yet. Start recording your runs!");
        } else {
            // Display the saved logs
            tvSavedLogs.setText(logs);
        }
    }
}