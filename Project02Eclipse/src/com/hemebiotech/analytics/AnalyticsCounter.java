package com.hemebiotech.analytics;

import com.hemebiotech.analytics.processor.AnalyticsCounterProcessor;
import com.hemebiotech.analytics.reader.ReadSymptomDataFromFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AnalyticsCounter {


    /**
     * Main function of Analytics Program that create an {@link AnalyticsCounter}
     * and call {@link AnalyticsCounter#process(String, String)}
     *
     * @param args not used
     */
    public static void main(String[] args) {
        AnalyticsCounter analyticsCounter = new AnalyticsCounter();
        analyticsCounter.process("symptoms.txt", "result.out");
    }

    /**
     * Default Constructor of {@link AnalyticsCounter}
     */
    public AnalyticsCounter() {

    }

    /**
     * Process function of Analytics Counter who
     * <p>
     * read the file "symptoms.txt",
     * process them with {@link AnalyticsCounterProcessor},
     * then write the counters to "result.out" using {@link AnalyticsCounter#writeCounters(String, List, Map)} )}
     *
     * @param inputFilePath not used
     */
    public void process(String inputFilePath, String outputFilePath) {
        // Read symptoms from file : symptoms.txt
        ReadSymptomDataFromFile reader = new ReadSymptomDataFromFile(inputFilePath);
        List<String> symptoms = reader.getSymptoms();

        // Process symptoms in an Analytics Counter Processor
        AnalyticsCounterProcessor processor = new AnalyticsCounterProcessor();
        processor.processSymptoms(symptoms);

        // Retrieve counters
        Map<String, Integer> counters = processor.getCounters();

        // Sort keys of counters (symptoms)
        List<String> symptomsSorted = sortSymptomsByAlphabeticOrder(counters.keySet());

        // Write counters to : result.out
        writeCounters(outputFilePath, symptomsSorted, counters);
    }

    public List<String> sortSymptomsByAlphabeticOrder(Collection<String> symptoms){
        // Clone the collection
        List<String> sorted = new ArrayList<>(symptoms);

        // Sort in Alphabetic Order
        sorted.sort(String::compareTo);
        return sorted;
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
    public void writeCounters(String filePath, List<String> symptomsSorted, Map<String, Integer> counters) {
        try {
            // Open file to write analytics
            FileWriter writer = new FileWriter(filePath);

            // Foreach symptom
            for (String symptom : symptomsSorted) {
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
