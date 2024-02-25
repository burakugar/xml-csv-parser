package com.tieotoevry.tietoevryxmlcsvparser;

import com.tieotoevry.tietoevryxmlcsvparser.strategy.CSVOutputStrategy;
import com.tieotoevry.tietoevryxmlcsvparser.strategy.FileOutputStrategy;
import com.tieotoevry.tietoevryxmlcsvparser.strategy.XMLOutputStrategy;
import com.tieotoevry.tietoevryxmlcsvparser.util.FileProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TietoevryXmlCsvParserApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        String format;

        while (true) {
            System.out.println("Enter the file name (or 'q' to quit):");
            fileName = scanner.nextLine();

            if ("q".equalsIgnoreCase(fileName)) {
                break; // Exit the loop and end the program
            }

            System.out.println("Select the output format (csv/xml):");
            format = scanner.nextLine();

            FileOutputStrategy strategy;
            switch (format.toLowerCase()) {
                case "csv":
                    strategy = new CSVOutputStrategy();
                    break;
                case "xml":
                    strategy = new XMLOutputStrategy();
                    break;
                default:
                    System.out.println("Invalid format. Please enter 'csv' or 'xml'.");
                    continue;
            }

            FileProcessor.processFile(fileName, strategy);
        }

        scanner.close();
    }

}



