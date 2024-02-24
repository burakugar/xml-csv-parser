package com.tieotoevry.tietoevryxmlcsvparser.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record Sentence(List<Word> words) {
    public Sentence(List<Word> words) {
        this.words = words.stream()
                .sorted(Comparator.comparing(Word::value))
                .collect(Collectors.toList());
    }
}

