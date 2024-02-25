package com.tieotoevry.tietoevryxmlcsvparser.strategy;

import com.tieotoevry.tietoevryxmlcsvparser.model.Sentence;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Implements the {@link FileOutputStrategy} for generating XML output.
 * This strategy processes the input text, breaks it into sentences, and
 * outputs each sentence and its words within an XML structure.
 */
public class XMLOutputStrategy implements FileOutputStrategy {
    static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>";
    private static final Pattern PATTERN = Pattern.compile("\\.\\s*");

    /**
     * Processes the given text and outputs it in XML format.
     * Each sentence from the input text is represented as an XML element,
     * with each word within a sentence also wrapped in its own XML element.
     *
     * @param text The input text to be processed. Must not be null.
     * @throws IllegalArgumentException If the text parameter is null.
     */
    @Override
    public void processAndOutput(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The text cannot be null ");
        }

        final String xmlOutput = PATTERN.splitAsStream(text)
                .map(Sentence::new)
                .map(sentence -> {
                    StringBuilder sentenceXml = new StringBuilder("<sentence>");
                    sentence.words().forEach(word -> sentenceXml.append("<word>").append(word).append("</word>"));
                    sentenceXml.append("</sentence>");
                    return sentenceXml.toString();
                })
                .collect(Collectors.joining("\n  ", XML_HEADER + "\n  ", "\n</text>"));

        System.out.println(xmlOutput);
    }
}
