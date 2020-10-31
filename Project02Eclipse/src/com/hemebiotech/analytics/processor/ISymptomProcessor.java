package com.hemebiotech.analytics.processor;

import com.hemebiotech.analytics.reader.ISymptomReader;

import java.util.List;

/**
 * Interface that process list of symptoms read from {@link ISymptomReader}
 *
 * @see ISymptomReader#getSymptoms()
 */
public interface ISymptomProcessor {

    /**
     * Process list of symptoms that contain possible/probable duplications
     *
     * @param symptoms list of symptoms (may contain many duplications)
     */
    void processSymptoms(List<String> symptoms);

}
