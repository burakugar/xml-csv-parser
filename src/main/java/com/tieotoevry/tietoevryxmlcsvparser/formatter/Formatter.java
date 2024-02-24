package com.tieotoevry.tietoevryxmlcsvparser.formatter;

import com.tieotoevry.tietoevryxmlcsvparser.model.Sentence;

import java.util.List;

public interface Formatter {
    String format(List<Sentence> sentences);
}