package com.example.abdulrahimfyp;

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

/**
 * ZonesFragment.java - Heart Rate Zone Calculator (Zones Tab)
 * ============================================================
 * This is the core feature of the application. It allows users to:
 * 1. Enter their age
 * 2. Calculate their maximum heart rate using the Karvonen formula (220 - age)
 * 3. View all five heart rate zones with their respective percentage ranges

 * The fragment uses a simple, user-friendly interface with:
 * - An age input field (restricted to numbers)
 * - A "Calculate Zones" button
 * - Display of Max HR and all five zones with colour-coded ranges

 * This is the default tab displayed when the app opens.
 */

public class ZonesFragment extends Fragment {


    // View References

    private EditText etAge;           // Age input fields
    private Button btnCalculate;      // Calculate Zones Button
    private TextView tvMaxHr;         // Displays calculated Max HR
    private TextView tvZones;         // Displays all five zone ranges


    // Fragment Lifecycle Methods

    /**
     * Called when the fragment is first created.
     * Inflates the layout, initialises views, and sets up the calculate button.

     * @param inflater           The LayoutInflater used to inflate the view
     * @param container          The parent ViewGroup that this fragment is attached to
     * @param savedInstanceState Saved instance state (if any)
     * @return The inflated View for this fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_zones layout
        View view = inflater.inflate(R.layout.fragment_zones, container, false);


        // 1. Initialise Views
        etAge = view.findViewById(R.id.etAge);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        tvMaxHr = view.findViewById(R.id.tvMaxHr);
        tvZones = view.findViewById(R.id.tvZones);


        // 2. Set up Calculate button click listener
        btnCalculate.setOnClickListener(v -> {
            // Get user input and trim whitespace
            String ageStr = etAge.getText().toString().trim();


            // Validation checks (Edge Cases)

            // Edge Case 1: Empty Input
            // Check if the user left teh age field empty
            if (ageStr.isEmpty()) {
                Toast.makeText(getContext(), "Please enter your age", Toast.LENGTH_SHORT).show();
                return;  // Stop execution and let user correct the input
            }

            // Edge Case 2: Non-numeric Input
            // Attempt to parse the input as an integer.
            // If parsing fails, the user entered non-numeric characters.
            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                // Catch block handles invalid input (e.g., "abc", "12.5")
                Toast.makeText(getContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                return;   // Stop execution and let user correct the input
            }


            // Edge Case 3: Age out of range
            // Validate that the age is within a realistic range (10 - 80)
            // This prevents unrealistic calculations and ensures meaningful results
            if (age < 10 || age > 80) {
                Toast.makeText(getContext(), "Please enter an age between 10 and 80", Toast.LENGTH_SHORT).show();
                return;   // Stop execution and let user correct the input
            }

            // 3. Calculate Heart Rate Zones

            // Calculate Max Herat Rate
            // The Karvonen formula (220 - age) is a simple, widely recognised method for estimating maximum heart rate.
            // It's suitable for educational purposes and the target audience of beginners.
            int maxHr = 220 - age;
            tvMaxHr.setText("Max HR: " + maxHr + " bpm");

            // Calculate and displays all five zones
            // Each Zone is calculated as a percentage range of the Max HR
            // String.format("%.2f") ensures consistent decimal formatting (2 decimal places).


            String zones = "Zone 1 (50-60%): " + String.format("%.2f", maxHr * 0.5) + " - " + String.format("%.2f", maxHr * 0.6) + "\n" +
                    "Zone 2 (60-70%): " + String.format("%.2f", maxHr * 0.6) + " - " + String.format("%.2f", maxHr * 0.7) + "\n" +
                    "Zone 3 (70-80%): " + String.format("%.2f", maxHr * 0.7) + " - " + String.format("%.2f", maxHr * 0.8) + "\n" +
                    "Zone 4 (80-90%): " + String.format("%.2f", maxHr * 0.8) + " - " + String.format("%.2f", maxHr * 0.9) + "\n" +
                    "Zone 5 (90-100%): " + String.format("%.2f", maxHr * 0.9) + " - " + maxHr;

            // Display the calculated zones in the TextView
            tvZones.setText(zones);
        });

        return view;
    }
}