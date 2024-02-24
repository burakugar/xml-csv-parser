package com.tieotoevry.tietoevryxmlcsvparser.formatter;

import com.tieotoevry.tietoevryxmlcsvparser.model.Sentence;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CsvFormatter implements Formatter {
    @Override
    public String format(List<Sentence> sentences) {
        AtomicInteger sentenceCounter = new AtomicInteger(1);
        StringBuilder csvBuilder = new StringBuilder(", Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8\n");
        sentences.forEach(sentence -> {
            String sentenceLine = sentence.words().stream()
                    .map(word -> word.value())
                    .collect(Collectors.joining(", "));
            csvBuilder.append("Sentence ").append(sentenceCounter.getAndIncrement()).append(", ").append(sentenceLine).append("\n");
        });
        return csvBuilder.toString();
    }
}

