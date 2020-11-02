package com.hemebiotech.analytics.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Process list of symptoms to analyse number of occurrences of each symptom
 */
public class AnalyticsCounterProcessor implements ISymptomProcessor {

    /**
     * @see Map
     * - key = symptom
     * - value = number of occurrences
     */
    private Map<String, Integer> counters;

    /**
     * Constructor of {@link AnalyticsCounterProcessor}
     */
    public AnalyticsCounterProcessor() {
        counters = new HashMap<>(); // initialize counters to empty map
    }

    /**
     * Implementation of {@link ISymptomProcessor#processSymptoms(List)}
     * <p>
     * Foreach symptom :
     * if it's a new symptom then create new counter
     * increment counter by 1
     *
     * @param symptoms list of symptoms (may contain many duplications)
     */
    @Override
    public void processSymptoms(List<String> symptoms) {
        for (String symptom : symptoms) { // Foreach symptoms
            // If it's new symptom then create new counter
            if (!counters.containsKey(symptom)) {
                counters.put(symptom, 0);
            }
            // get previous count and add 1
            int previousCount = counters.get(symptom);
            counters.put(symptom, previousCount + 1);
        }
    }

    /**
     * Getter of {@link AnalyticsCounterProcessor#counters}
     *
     * @return return counters variable
     */
    public Map<String, Integer> getCounters() {
        return counters;
    }
}
