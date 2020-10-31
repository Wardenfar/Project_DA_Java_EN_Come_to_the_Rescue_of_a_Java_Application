package com.hemebiotech.analytics.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read one symptoms per line from file
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

    private String filepath;

    /**
     * {@link ReadSymptomDataFromFile} Constructor
     *
     * @param filepath a full or partial path to file with symptom strings in it, one per line
     */
    public ReadSymptomDataFromFile(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Implementation of {@link ISymptomReader#getSymptoms()}
     *
     * @return list of symptoms (may contains duplicates)
     */
    @Override
    public List<String> getSymptoms() {
        // Initialize list of symptoms
        ArrayList<String> symptoms = new ArrayList<>();

        if (filepath != null) {
            try { // Catch errors while reading the file
                BufferedReader reader = new BufferedReader(new FileReader(filepath));

                // Read the first line
                String line = reader.readLine();

                while (line != null) { // While there is another line in the file
                    if (!line.isBlank()) { // Add line to symptoms only if it's not blank
                        symptoms.add(line);
                    }
                    line = reader.readLine(); // Read the next line and repeat
                }

                // Close the reader
                reader.close();
            } catch (IOException e) {
                System.err.println("Error while reading the file: " + filepath);
                e.printStackTrace();
            }
        }

        return symptoms;
    }
}
