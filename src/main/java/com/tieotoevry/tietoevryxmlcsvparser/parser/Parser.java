package com.tieotoevry.tietoevryxmlcsvparser.parser;

import com.tieotoevry.tietoevryxmlcsvparser.model.Sentence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.tieotoevry.tietoevryxmlcsvparser.model.Word;

public class Parser {

    public static List<Sentence> parseTextToSentences(String text) {
        return Arrays.stream(text.split("\\."))
                .map(sentence -> new Sentence(Arrays.stream(sentence.trim().split("\\s+"))
                        .map(Word::new)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
