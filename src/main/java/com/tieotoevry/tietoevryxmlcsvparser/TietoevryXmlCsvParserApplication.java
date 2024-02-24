package com.tieotoevry.tietoevryxmlcsvparser;

import com.tieotoevry.tietoevryxmlcsvparser.util.FileProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TietoevryXmlCsvParserApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println("Enter the file name (or 'q' to quit):");
            userInput = scanner.nextLine();

            if ("q".equalsIgnoreCase(userInput)) {
                break; // Exit the loop and end the program
            }

            FileProcessor.processFile(userInput);
        }
    }

}



