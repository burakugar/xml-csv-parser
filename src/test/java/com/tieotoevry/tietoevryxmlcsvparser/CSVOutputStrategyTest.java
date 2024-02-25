package com.tieotoevry.tietoevryxmlcsvparser;

import com.tieotoevry.tietoevryxmlcsvparser.strategy.CSVOutputStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVOutputStrategyTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final String BASE_PATH = "src/main/java/com/tieotoevry/tietoevryxmlcsvparser/input/";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testProcessAndOutputFromFile() throws Exception {
        // Replace "small.in" with the actual file name if different
        final String fileName = "small.in";
        final String fullPath = Paths.get(BASE_PATH, fileName).toString();

        // Read the content from the file
        String fileContent = Files.lines(Paths.get(fullPath)).collect(Collectors.joining("\n"));

        // Instantiate your strategy and process the file content
        CSVOutputStrategy strategy = new CSVOutputStrategy();
        strategy.processAndOutput(fileContent);

        // Define your expected output here
        final String expectedOutput = "Sentence, Words\n" +
                "Sentence 1, a, because, Chinese, couldn't, he, I, isn't, mother, my, shocking:, shouted, tongue, understand, was, What, word,perhaps, 你这肮脏的掠夺者!, 停在那儿,\n" +
                "Sentence 2, I, just, Mr, standing, there, was, watching\n" +
                "Sentence 3, -, around, furious, he, marching, was, Young\n" +
                "Sentence 4, about, anger, at, did, directing, he, his, I, know, Little, me?, that, was, Why\n" +
                "Sentence 5, and, and, Baltic, banking, capital, in, international, investment, is, leading, Markets, markets, Nordea, Nordic, operator, partner, regions, Sea, the, the\n" +
                "Sentence 6, ,, are, connecting, door, global, located, markets, next, the, to, to, We, you, you\n" +
                "Sentence 7, a, and, combine, complete, expertise, financial, global, local, of, portfolio, provide, services, solutions, strength, to, We, with, with, you\n" +
                "Sentence 8, and, currencies, diversified, have, in, in, liquidity, local, most, Nordics, of, offer, one, outstanding, product, ranges, strongest,, the, the, We\n" +
                "Sentence 9, ,in, access, all, an, best, But, capital, dedicated, experts, facets, in, manner, markets,, more, of, of, offer, possible, serving, significantly,, team, the, to, to, unequalled, we, you, you\n" +
                "Sentence 10, a, a, and, and, At, combination, customer,, expertise, financial, for, gives, global, have, local, Markets, Nordea, of, of, opportunity, our, rare, services, solutions, strength, the, to, us, use, variety, we, which, wide, you,\n" +
                "Sentence 11, (and, -, a, a, all, and, as, as, can, currencies, diversified, excellent, fact, finding, give, hard, have, In, in, in, liquidity, local, Nordics,, of, ours, product, range, strong, the, time, too), we, you, you’d\n" +
                "Sentence 12, a, be, But, challenge, financial, have, huge, importantly,, matter, might, most, no, of, outstanding, ready, serve, specialists, team, to, we, what, you,, your";

        // Assert that the actual output matches the expected output
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void test_processAndOutput_throwsIllegalArgumentException() {
        final String text = null;

        CSVOutputStrategy strategy = new CSVOutputStrategy();

        assertThrows(IllegalArgumentException.class, () -> {
            strategy.processAndOutput(text);
        });
    }

}


