package com.tieotoevry.tietoevryxmlcsvparser.strategy;

import com.tieotoevry.tietoevryxmlcsvparser.model.Sentence;

import java.util.Arrays;
import java.util.List;

/**
 * Implements the {@link FileOutputStrategy} for generating CSV output.
 * This strategy takes a text input, processes it into sentences and words,
 * and outputs the result in CSV format, where each line represents a sentence
 * and its words sorted alphabetically.
 */
public class CSVOutputStrategy implements FileOutputStrategy {

    /**
     * Processes the given text and outputs it in CSV format.
     * Each sentence from the input text is split into words, sorted,
     * and then output as a line in the CSV format with the sentence number
     * and the sorted words.
     *
     * @param text The input text to be processed. Must not be null.
     * @throws IllegalArgumentException If the text parameter is null.
     */
    @Override
    public void processAndOutput(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The text cannot be null");
        }

        List<Sentence> sentences = Arrays.stream(text.split("\\.\\s*"))
                .map(Sentence::new) // This creates a new Sentence object for each sentence text
                .toList();

        StringBuilder output = new StringBuilder("Sentence, Words\n");
        int sentenceNumber = 1;
        for (Sentence sentence : sentences) {
            // Get the list of words from the Sentence object
            List<String> words = sentence.words();
            output.append("Sentence ").append(sentenceNumber++).append(", ")
                    .append(String.join(", ", words)).append("\n");
        }
        System.out.println(output);
    }
}
