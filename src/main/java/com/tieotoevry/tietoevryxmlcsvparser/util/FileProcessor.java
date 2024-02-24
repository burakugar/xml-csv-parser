package com.tieotoevry.tietoevryxmlcsvparser.util;

import com.tieotoevry.tietoevryxmlcsvparser.formatter.Formatter;
import com.tieotoevry.tietoevryxmlcsvparser.formatter.FormatterFactory;
import com.tieotoevry.tietoevryxmlcsvparser.model.Sentence;
import com.tieotoevry.tietoevryxmlcsvparser.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProcessor {
    private static final String basePath = "src/main/java/com/tieotoevry/tietoevryxmlcsvparser/input/";

/*    public static void processFile(String fileName) {
        final String fullPath = Paths.get(basePath, fileName).toString();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter output format (XML or CSV):");


        String format = scanner.nextLine().trim().toLowerCase();
        Formatter formatter = FormatterFactory.createFormatter(format);

        try (Stream<String> lines = Files.lines(Paths.get(fullPath))) {
            StringBuilder contentBuilder = new StringBuilder();
            lines.forEach(line -> contentBuilder.append(line).append(" ")); // Accumulate lines, considering a space as delimiter
            String content = contentBuilder.toString();
            List<Sentence> sentences = Parser.parseTextToSentences(content); // Ensure your parser can handle partial inputs if needed
            System.out.println(formatter.format(sentences));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }*/


    public static void processFile(String fileName) {
        final String fullPath = Paths.get(basePath, fileName).toString();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fullPath))) {
            String line;
            StringBuilder sentenceAccumulator = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sentenceAccumulator.append(line);
                // Assuming sentences end with "." and processing when detected
                if (line.contains(".")) {
                    processAndOutput(sentenceAccumulator.toString(), "xml");
                    sentenceAccumulator = new StringBuilder(); // Reset for the next sentence
                }
            }
            // Process any remaining text that might not end with a period
            if (sentenceAccumulator.length() > 0) {
                processAndOutput(sentenceAccumulator.toString(), "xml");
            }
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }

    }

    private static void processAndOutput(String text, String format) {
        // Split text into sentences, assuming "." as the end of a sentence
        String[] sentences = text.split("\\.\\s*");
        StringBuilder output = new StringBuilder();

        if ("xml".equals(format)) {
            output.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>");
        } else if ("csv".equals(format)) {
            output.append("Sentence, Words\n");
        }

        int sentenceNumber = 1;
        for (String sentence : sentences) {
            List<String> words = Arrays.stream(sentence.trim().split("\\s+"))
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .collect(Collectors.toList());

            if ("xml".equals(format)) {
                output.append("\n  <sentence>");
                words.forEach(word -> output.append("\n    <word>").append(word).append("</word>"));
                output.append("\n  </sentence>");
            } else if ("csv".equals(format)) {
                output.append("Sentence ").append(sentenceNumber++).append(", ")
                        .append(String.join(", ", words)).append("\n");
            }
        }

        if ("xml".equals(format)) {
            output.append("\n</text>");
        }

        System.out.println(output.toString());
    }


}
