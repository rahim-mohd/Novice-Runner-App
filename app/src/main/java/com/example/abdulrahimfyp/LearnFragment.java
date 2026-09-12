package com.example.abdulrahimfyp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * LearnFragment.java - Educational Zone Guide (Learn Tab)
 * This fragment serves as the educational hub of the application.
 * It displays:
 * - A comprehensive guide to all five heart rate zones with colour coding
 * - A beginner tip box with practical advice
 * - A "View Full Glossary" button that navigates to the searchable glossary

 * The fragment uses a ScrollView to accommodate all content in a single
 * scrollable page. Users can tap any zone to view detailed explanations.

 * Navigation:
 * - The "View Full Glossary" button uses FragmentTransaction to replace
 *   the current fragment with GlossaryFragment.
 * - The transaction is added to the back stack, allowing users to press
 *   the Back button to return to this fragment.
 */

public class LearnFragment extends Fragment {

    // Fragment Lifecycle Methods
    /**
     * Called when the fragment is first created.
     * Inflates the layout, initialises views, and sets up the glossary button.

     * @param inflater           The LayoutInflater used to inflate the view
     * @param container          The parent ViewGroup that this fragment is attached to
     * @param savedInstanceState Saved instance state (if any)
     * @return The inflated View for this fragment
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_learn layout
        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        // 1. Initialise the Views
        // Find the "View Full Glossary" button in the layour
        Button btnViewGlossary = view.findViewById(R.id.btnViewGlossary);

        // 2. Set up Glossary Button Click Listener
        btnViewGlossary.setOnClickListener(v -> {

            // Navigate to GlossaryFragment
            // Use FragmentManager to replace the current fragment with GlossaryFragment
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // Replace the fragment container with GlossaryFragment
            transaction.replace(R.id.fragment_container, new GlossaryFragment());

            // Add this transaction to the back stack.
            // This allows the user to press the Back button to return to LearnFragment.
            transaction.addToBackStack(null);

            // Commit the transaction (perform the fragment replacement)
            transaction.commit();
        });

        return view;
    }
}