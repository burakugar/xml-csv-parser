package com.tieotoevry.tietoevryxmlcsvparser.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Represents a sentence consisting of a list of words.
 */
public record Sentence(List<String> words) {

    /**
     * Constructs a Sentence object from the given sentence text.
     *
     * @param sentenceText the sentence text
     */
    public Sentence(String sentenceText) {
        this(sort(sentenceText));
    }

    /**
     * Sorts the words in the sentence text and returns a list of sorted words.
     *
     * @param sentenceText the sentence text
     * @return a list of sorted words
     * @throws IllegalArgumentException if the sentence text is null
     */
    private static List<String> sort(String sentenceText) {
        if (sentenceText == null) {
            throw new IllegalArgumentException("sentenceText cannot be null");
        }
        String[] wordArray = sentenceText.trim().split("\\s+");
        Arrays.sort(wordArray, String.CASE_INSENSITIVE_ORDER);
        return new ArrayList<>(Arrays.asList(wordArray));
    }

}
