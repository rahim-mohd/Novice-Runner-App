package com.example.abdulrahimfyp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * PlanFragment.java - Weekly Training Plan (Plan Tab)
 * This fragment displays a weekly training schedule based on the
 * polarised (80/20) training model:
 * - 80% easy runs (Zone 2) for building aerobic base
 * - 20% hard efforts (Zone 4-5) for improving performance

 * The layout is static and purely informational.
 * It shows a 7-day schedule with colour-coded activities:
 * - Green (#E8F5E9) = Easy runs (Zone 2)
 * - Orange (#FFF3E0) = Rest days
 * - Red (#FFEBEE) = Hard efforts (Intervals)
 * - Blue (#E3F2FD) = Recovery / Zone 1

 * No dynamic logic is required as the plan is fixed for beginners.
 * This simplicity aligns with the app's goal of providing clear,
 * accessible guidance without overwhelming novice users.
 */

public class PlanFragment extends Fragment {


    // Fragment Lifecycle Methods

    /**
     * Called when the fragment is first created.
     * Inflates the fragment_plan layout which contains the static
     * weekly training schedule.

     * @param inflater           The LayoutInflater used to inflate the view
     * @param container          The parent ViewGroup that this fragment is attached to
     * @param savedInstanceState Saved instance state (if any)
     * @return The inflated View for this fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_plan layout and return it
        // No additional logic is needed as the content is static
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }
}