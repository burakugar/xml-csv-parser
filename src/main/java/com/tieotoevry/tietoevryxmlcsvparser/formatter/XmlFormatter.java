package com.tieotoevry.tietoevryxmlcsvparser.formatter;

import com.tieotoevry.tietoevryxmlcsvparser.model.Sentence;

import java.util.List;
public class XmlFormatter implements Formatter {
    @Override
    public String format(List<Sentence> sentences) {
        StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>");
        sentences.forEach(sentence -> {
            xmlBuilder.append("\n  <sentence>");
            sentence.words().stream()
                    .sorted((w1, w2) -> w1.value().compareToIgnoreCase(w2.value())) // Ensure words are sorted
                    .forEach(word ->
                            xmlBuilder.append("\n    <word>").append(word.value()).append("</word>"));
            xmlBuilder.append("\n  </sentence>");
        });
        xmlBuilder.append("\n</text>");
        return xmlBuilder.toString();
    }
}
