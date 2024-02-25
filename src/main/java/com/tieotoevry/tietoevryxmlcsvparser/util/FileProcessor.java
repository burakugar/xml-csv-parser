package com.tieotoevry.tietoevryxmlcsvparser.util;

import com.tieotoevry.tietoevryxmlcsvparser.strategy.FileOutputStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Handles the processing of files by reading from a specified path,
 * accumulating sentences, and utilizing a provided {@link FileOutputStrategy}
 * to output the processed text.
 */
public class FileProcessor {
    private static final String BASE_PATH = "src/main/java/com/tieotoevry/tietoevryxmlcsvparser/input/";
    private static final Logger LOGGER = Logger.getLogger(FileProcessor.class.getName());

    /**
     * Processes the file specified by fileName using the given output strategy.
     * Reads the file line by line, accumulates sentences, and passes them to
     * the strategy for processing and outputting.
     * 
     * This method assumes that a sentence ends with a period ("."). If the accumulator
     * contains text after reading the entire file, it processes this remaining text
     * as a sentence as well.
     *
     * @param fileName The name of the file to be processed, relative to the {@link #BASE_PATH}.
     * @param strategy The {@link FileOutputStrategy} to be used for processing and outputting
     *                 the accumulated sentences.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public static void processFile(String fileName, FileOutputStrategy strategy) {
        LOGGER.info("Processing file: " + fileName);
        final String fullPath = Paths.get(BASE_PATH, fileName).toString();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fullPath))) {
            String line;
            StringBuilder sentenceAccumulator = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sentenceAccumulator.append(line);
                if (line.contains(".")) {
                    strategy.processAndOutput(sentenceAccumulator.toString());
                    sentenceAccumulator = new StringBuilder(); // Reset for the next sentence
                }
            }
            // Process any remaining text as a sentence
            if (sentenceAccumulator.length() > 0) {
                strategy.processAndOutput(sentenceAccumulator.toString());
            }
            LOGGER.info("Finished processing file: " + fileName);
        } catch (IOException e) {
            LOGGER.severe("Error processing the file: " + e.getMessage());
        }
    }
}
