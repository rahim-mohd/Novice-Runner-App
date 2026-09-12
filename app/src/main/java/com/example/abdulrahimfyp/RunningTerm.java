package com.example.abdulrahimfyp;

/**
 * RunningTerm.java - data model for glossary Terms
 * =========
 * This class represents a single running term in the glossary.
 * Each term has:
 * - A term name (e.g "Aerobic")
 * - A Category (e.g "Training")
 * - A definition/explaination
 * ====
 * TermAdapter to populate the RecyclerView in the GlossaryFragment
 */


public class RunningTerm {
    // Member Variable (Fields).
    //These are private to enforce encapsulation (data hiding)
    //Access is provided through getter and setter methods.

    /**
     * The name of the running term.
     * Example: "Aerobic", "Cadence", "Fartlek"
     */

    private String term;
    /**
     * The category this term belongs to.
     * Used for visual grouping and filtering.
     * Examples: "Training", "Form", "Pace", "Workouts"
     */

    private String category;
    /**
     * The full definition or explanation of the term.
     * This is the educational content displayed to the user.
     */

    private String definition;

    //Constructor
    /**
     * Constructor - Creates a new RunningTerm object.
     * All fields are required and set during object creation.
     *
     * @param term       The name of the running term (e.g., "Aerobic")
     * @param category   The category this term belongs to (e.g., "Training")
     * @param definition The full definition/explanation (e.g., "Exercise where oxygen...")
     */

    public RunningTerm(String term, String category, String definition) {
        this.term = term;
        this.category = category;
        this.definition = definition;
    }

    // Getter Methods (Accessors)
    // These methods allow external classes to read the private fields.
    // They follow the standard Java naming convention: get + FieldName.

    /**
     * Returns the term name.
     * @return The term name as a String
     */
    public String getTerm() { return term; }

    /**
     * Returns the category.
     * @return The category as a String
     */
    public String getCategory() { return category; }

    /**
     * Returns the definition.
     * @return The definition as a String
     */
    public String getDefinition() { return definition; }

    // Setter Methods (Mutators)
    // These methods allow external classes to modify the private fields.
    // They follow the standard Java naming convention: set + FieldName.
    /**
     * Sets the term name.
     * @param term The new term name
     */
    public void setTerm(String term) { this.term = term; }

    /**
     * Sets the category.
     * @param category The new category
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Sets the definition.
     * @param definition The new definition
     */
    public void setDefinition(String definition) { this.definition = definition; }
}