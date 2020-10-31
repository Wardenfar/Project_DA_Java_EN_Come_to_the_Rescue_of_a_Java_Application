package com.hemebiotech.analytics;

import com.hemebiotech.analytics.processor.AnalyticsCounterProcessor;
import com.hemebiotech.analytics.reader.ReadSymptomDataFromFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalyticsCounter {

    /**
     * Main function of Analytics Program who
     * <p>
     * read the file "symptoms.txt",
     * process them with {@link AnalyticsCounterProcessor},
     * then write the counters to "result.out" using {@link AnalyticsCounter#writeCountersInAlphabeticOrder(String, Map)}
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Read symptoms from file : symptoms.txt
        ReadSymptomDataFromFile reader = new ReadSymptomDataFromFile("symptoms.txt");
        List<String> symptoms = reader.getSymptoms();

        // Process symptoms in an Analytics Counter Processor
        AnalyticsCounterProcessor processor = new AnalyticsCounterProcessor();
        processor.processSymptoms(symptoms);

        // Retrieve counters
        Map<String, Integer> counters = processor.getCounters();

        // Write counters to : result.out
        writeCountersInAlphabeticOrder("result.out", counters);
    }

    /**
     * Write counters in the file specified, one line per counter with the format :
     * <p>
     * [symptom]: [count]
     * [symptom]: [count]
     * ...
     *
     * @param filePath full or relative path to file to write counters
     * @param counters counters from {@link AnalyticsCounterProcessor#getCounters()}
     */
    public static void writeCountersInAlphabeticOrder(String filePath, Map<String, Integer> counters) {
        try {
            // Open file to write analytics
            FileWriter writer = new FileWriter(filePath);

            // Get keys (symptoms) of counters (no duplicates)
            List<String> symptoms = new ArrayList<>(counters.keySet());

            // Sort in Alphabetic Order
            symptoms.sort(String::compareTo);

            // Foreach symptom
            for (String symptom : symptoms) {
                int count = counters.get(symptom);

                // Write line with format: "[symptom]: [count]\n"
                writer.write(symptom);
                writer.write(": ");
                writer.write(String.valueOf(count));
                writer.write("\n");
            }

            // Close writer
            writer.close();
        } catch (IOException e) {
            System.err.println("Error while writing counters to file : " + filePath);
            e.printStackTrace();
        }
    }
}
