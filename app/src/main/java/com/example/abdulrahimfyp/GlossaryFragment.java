package com.example.abdulrahimfyp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * GlossaryFragment.java - Searchable Running Glossary
 * This fragment displays a searchable list of running terms and definitions.

 * Features:
 * - A SearchView for real-time filtering of terms
 * - A RecyclerView that displays the list of terms
 * - Each term includes name, category, and definition

 * Search Functionality:
 * - Users can search by term name, definition, or category
 * - The search is case-insensitive and filters in real-time
 * - Clearing the search query resets the list to show all terms

 * Data Source:
 * - Terms are stored in-memory as a list of RunningTerm objects
 * - The list is populated in loadTerms() and displayed via TermAdapter

 * This fragment is accessed from the LearnFragment via the "View Full Glossary" button.
 */

public class GlossaryFragment extends Fragment {

    // View References

    private RecyclerView rvTerms;       // RecycleView for displaying terms
    private TermAdapter termAdapter;    // Adapter for the RecyclerView
    private List<RunningTerm> termList; // List of all terms
    private SearchView searchView;      // Search bar for filtering terms


    // Fragment Lifecycle Methods
    /**
     * Called when the fragment is first created.
     * Initialises views, sets up the RecyclerView, loads terms,
     * and configures the SearchView.

     * @param inflater           The LayoutInflater used to inflate the view
     * @param container          The parent ViewGroup that this fragment is attached to
     * @param savedInstanceState Saved instance state (if any)
     * @return The inflated View for this fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_glossary layout
        View view = inflater.inflate(R.layout.fragment_glossary, container, false);


        // 1. Initialise Views
        rvTerms = view.findViewById(R.id.rvTerms);
        searchView = view.findViewById(R.id.searchView);

        // 2. Set up Recyclerview
        // Use LinearLayoutManager for vertical scrolling list
        rvTerms.setLayoutManager(new LinearLayoutManager(getContext()));

        // setHasFixedSize(true) optimises performance by telling the RecyclerView
        // that the size of each item is fixed and won't change.
        rvTerms.setHasFixedSize(true);

        // 3. Load Terms and Set up adapter
        // Populate the term list with data
        loadTerms();

        // Create and set the adapter with the term list
        termAdapter = new TermAdapter(termList);
        rvTerms.setAdapter(termAdapter);


        // 4. Set up SearchView
        //Handle search query changes in real-time
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             * Called when the user submits a search query (e.g., presses Enter).
             * @param query The search query text
             * @return true if the event was handled
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Apply the filter with the submitted query
                termAdapter.filter(query);
                return false;  // Return false to allow the SearchView to handle the event
            }

            /**
             * Called when the search query text changes (real-time filtering).
             * This is the main method for live search as the user types.
             * @param newText The new search query text
             * @return true if the event was handled
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                // Apply the filter with the current query text
                termAdapter.filter(newText);
                return false;   // Return false to allow the SearchView to handle the event
            }
        });

        // Handle search view close/clear event
        // Called when the user clears the search query by pressing the "X" button
        searchView.setOnCloseListener(() -> {
            // Reset the adapter to show all terms
            termAdapter.reset();
            return false;  // Return false to allow the SearchView to handle the event
        });

        return view;
    }

    // Data Loading Methods
    /**
     * Loads the list of running terms into memory.
     * Each term is a RunningTerm object with:
     * - term: The name of the term (e.g., "Aerobic")
     * - category: The category (e.g., "Training", "Form", "Pace", "Workouts")
     * - definition: The full explanation

     * Terms are organised by category for easier maintenance.
     * This method is called when the fragment is first created.
     */
    private void loadTerms() {
        termList = new ArrayList<>();

        // Training Terms
        termList.add(new RunningTerm("Aerobic", "Training", "Exercise where oxygen is the primary energy source. Most easy runs are aerobic."));
        termList.add(new RunningTerm("Anaerobic", "Training", "High-intensity exercise where the body can't supply enough oxygen, creating lactate."));
        termList.add(new RunningTerm("Base Building", "Training", "Phase focused on easy miles to build aerobic foundation before adding intensity."));
        termList.add(new RunningTerm("Lactate Threshold", "Training", "Intensity where lactate builds faster than it clears. Training here improves endurance."));

        // Form Terms
        termList.add(new RunningTerm("Cadence", "Form", "Number of steps per minute. Optimal cadence is usually 170–180 steps per minute."));

        // Pace Terms
        termList.add(new RunningTerm("Easy Pace", "Pace", "Comfortable pace where you can hold a conversation. Zone 1–2 heart rate."));
        termList.add(new RunningTerm("Negative Split", "Pace", "Running the second half of a run faster than the first half."));

        // Workout Terms
        termList.add(new RunningTerm("Fartlek", "Workouts", "Swedish for 'speed play'. Unstructured intervals mixing fast and slow running."));
        termList.add(new RunningTerm("Intervals", "Workouts", "Structured workout alternating between high-intensity efforts and recovery periods."));
        termList.add(new RunningTerm("Long Run", "Workouts", "Your longest weekly run, typically done at easy pace to build endurance."));
        termList.add(new RunningTerm("Recovery Run", "Workouts", "Very easy, short run (Zone 1) the day after a hard workout to promote recovery."));
        termList.add(new RunningTerm("Strides", "Workouts", "Short 20–30 second bursts at near-max effort, used to improve form and speed."));
        termList.add(new RunningTerm("Tempo Run", "Workouts", "Sustained run at comfortably hard pace (Zone 3), usually 20–40 minutes."));
    }
}