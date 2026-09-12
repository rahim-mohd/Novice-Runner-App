package com.example.abdulrahimfyp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * TermAdapter.java - RecyclerView Adapter for Glossary Terms
 * This adapter bridges the data (list of RunningTerm objects) with the RecyclerView UI in the GlossaryFragment.

 * Key Features:
 * - Displays a list of running terms with their categories and definitions
 * - Supports real-time search filtering
 * - Uses ViewHolder pattern for efficient view recycling

 * The adapter maintains two lists:
 * - termList: The currently displayed list (filtered)
 * - termListFull: The complete, unfiltered list (for reset)
 */
public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    // Member Variables
    /**
     * The current list of terms being displayed.
     * This list is modified when filtering is applied.
     */
    private List<RunningTerm> termList;

    /**
     * A copy of the full, unfiltered term list.
     * Used to reset the adapter when the search query is cleared.
     */
    private List<RunningTerm> termListFull;

    // ============================================================
    // Constructor
    // ============================================================
    /**
     * Constructor - Initialises the adapter with a list of terms.
     * Creates a separate copy (termListFull) for filtering purposes.
     *
     * @param termList The initial list of RunningTerm objects
     */

    public TermAdapter(List<RunningTerm> termList) {
        this.termList = termList;
        this.termListFull = new ArrayList<>(termList);
    }

    // RecyclerView.Adapter Override Methods
    /**
     * Called when the RecyclerView needs a new ViewHolder.
     * This method inflates the item_term.xml layout and creates
     * a TermViewHolder instance to hold the views.
     *
     * @param parent   The ViewGroup that will contain the new view
     * @param viewType The view type (not used here)
     * @return A new TermViewHolder
     */
    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_term, parent, false);
        return new TermViewHolder(view);
    }

    /**
     * Called to display data at a specific position.
     * This method binds a RunningTerm object to the ViewHolder's views.
     *
     * @param holder   The ViewHolder to bind data to @param position The position in the list (0-based)
     */
    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        RunningTerm term = termList.get(position);

        // Bind data to the views
        holder.tvTerm.setText(term.getTerm());                  // Term name (bold, large)
        holder.tvCategory.setText(term.getCategory());          // Category chip
        holder.tvDefinition.setText(term.getDefinition());      // Full definition
    }

    /**
     * Returns the total number of items in the current list.
     *
     * @return The size of termList
     */
    @Override
    public int getItemCount() {
        return termList.size();
    }

    // Filtering Methods

    /**
     *  Filters the term list based on a search query.
     *  Searches through term name, definition, and category.
     *  If the query is empty, the full list is restored.

     *  This method is called from the SearchView in GlossaryFragment whenever the user types or clears a search query.
     * @param text The search query (Case-sensitive)
     */
    public void filter(String text) {
        // Clear the current list before re-populating
        termList.clear();

        // Check if the search query is empty
        if (text.isEmpty()) {
            // If empty, restore the full list
            termList.addAll(termListFull);
        } else {
            // Convert search query to lowercase for case-insensitive matching
            String filterPattern = text.toLowerCase().trim();

            // Loop through all terms and add matches to the filtered list
            for (RunningTerm term : termListFull) {
                // Check if term name, definition or category contains the search query
                if (term.getTerm().toLowerCase().contains(filterPattern) ||
                        term.getDefinition().toLowerCase().contains(filterPattern) ||
                        term.getCategory().toLowerCase().contains(filterPattern)) {
                    termList.add(term);
                }
            }
        }

        // Notify the RecyclerView that the data has changed
        // This triggers a re-render of the visible items
        notifyDataSetChanged();
    }

    /**
     * Resets the adapter to show all terms (clear filter).
     * Called when the SearchView is closed or cleared
     */
    public void reset() {
        termList.clear();
        termList.addAll(termListFull);
        notifyDataSetChanged();
    }

    // ViewHolder Class

    /**
     * TermViewHolder - Holds references to views in a single item card.
     * This pattern improves performance by avoiding repeated findViewById() calls.

     * The ViewHolder caches references to:
     * - tvTerm: The term name (bold, larger text)
     * - tvCategory: The category chip (smaller, coloured background)
     * - tvDefinition: The full definition (smaller text below)
     */
    public static class TermViewHolder extends RecyclerView.ViewHolder {

        // View references
        TextView tvTerm;      // Term name (e.g "aerobic)
        TextView tvCategory;  // Category (e.g "Training)
        TextView tvDefinition; // Full definition

        /**
         * Constructor - Finds and stores references to views
         *
         * @param itemView The inflated item_term.xml view
         */

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);

            //Find views by their IDs
            tvTerm = itemView.findViewById(R.id.tvTerm);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvDefinition = itemView.findViewById(R.id.tvDefinition);
        }
    }
}