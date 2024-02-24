package com.tieotoevry.tietoevryxmlcsvparser.formatter;

public class FormatterFactory {
    public static Formatter createFormatter(String format) {
        switch (format.toLowerCase()) {
            case "xml":
                return new XmlFormatter();
            case "csv":
                return new CsvFormatter();
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
