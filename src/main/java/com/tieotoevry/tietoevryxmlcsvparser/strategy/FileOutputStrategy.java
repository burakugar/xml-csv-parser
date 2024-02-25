package com.tieotoevry.tietoevryxmlcsvparser.strategy;

/**
 * Defines a strategy for processing and outputting text.
 * Implementations of this interface can output the text in various formats,
 * such as CSV or XML, depending on the specific strategy used.
 */
public interface FileOutputStrategy {

    /**
     * Processes the given text and outputs it according to the specific
     * strategy implemented (e.g., CSV, XML).
     *
     * @param text The text to be processed and output. Must not be null.
     * @throws IllegalArgumentException If the text parameter is null.
     */
    void processAndOutput(String text);
}
