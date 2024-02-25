package com.tieotoevry.tietoevryxmlcsvparser;

import com.tieotoevry.tietoevryxmlcsvparser.strategy.XMLOutputStrategy;
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

class XMLOutputStrategyTest {

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
        final String fileContent = Files.lines(Paths.get(fullPath)).collect(Collectors.joining("\n"));

        // Instantiate your strategy and process the file content
        XMLOutputStrategy strategy = new XMLOutputStrategy();
        strategy.processAndOutput(fileContent);

        // Define your expected output here
        final String expectedOutput = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<text>\n" +
                "  <sentence><word>a</word><word>because</word><word>Chinese</word><word>couldn't</word><word>he</word><word>I</word><word>isn't</word><word>mother</word><word>my</word><word>shocking:</word><word>shouted</word><word>tongue</word><word>understand</word><word>was</word><word>What</word><word>word,perhaps</word><word>你这肮脏的掠夺者!</word><word>停在那儿,</word></sentence>\n" +
                "  <sentence><word>I</word><word>just</word><word>Mr</word><word>standing</word><word>there</word><word>was</word><word>watching</word></sentence>\n" +
                "  <sentence><word>-</word><word>around</word><word>furious</word><word>he</word><word>marching</word><word>was</word><word>Young</word></sentence>\n" +
                "  <sentence><word>about</word><word>anger</word><word>at</word><word>did</word><word>directing</word><word>he</word><word>his</word><word>I</word><word>know</word><word>Little</word><word>me?</word><word>that</word><word>was</word><word>Why</word></sentence>\n" +
                "  <sentence><word>and</word><word>and</word><word>Baltic</word><word>banking</word><word>capital</word><word>in</word><word>international</word><word>investment</word><word>is</word><word>leading</word><word>Markets</word><word>markets</word><word>Nordea</word><word>Nordic</word><word>operator</word><word>partner</word><word>regions</word><word>Sea</word><word>the</word><word>the</word></sentence>\n" +
                "  <sentence><word>,</word><word>are</word><word>connecting</word><word>door</word><word>global</word><word>located</word><word>markets</word><word>next</word><word>the</word><word>to</word><word>to</word><word>We</word><word>you</word><word>you</word></sentence>\n" +
                "  <sentence><word>a</word><word>and</word><word>combine</word><word>complete</word><word>expertise</word><word>financial</word><word>global</word><word>local</word><word>of</word><word>portfolio</word><word>provide</word><word>services</word><word>solutions</word><word>strength</word><word>to</word><word>We</word><word>with</word><word>with</word><word>you</word></sentence>\n" +
                "  <sentence><word>and</word><word>currencies</word><word>diversified</word><word>have</word><word>in</word><word>in</word><word>liquidity</word><word>local</word><word>most</word><word>Nordics</word><word>of</word><word>offer</word><word>one</word><word>outstanding</word><word>product</word><word>ranges</word><word>strongest,</word><word>the</word><word>the</word><word>We</word></sentence>\n" +
                "  <sentence><word>,in</word><word>access</word><word>all</word><word>an</word><word>best</word><word>But</word><word>capital</word><word>dedicated</word><word>experts</word><word>facets</word><word>in</word><word>manner</word><word>markets,</word><word>more</word><word>of</word><word>of</word><word>offer</word><word>possible</word><word>serving</word><word>significantly,</word><word>team</word><word>the</word><word>to</word><word>to</word><word>unequalled</word><word>we</word><word>you</word><word>you</word></sentence>\n" +
                "  <sentence><word>a</word><word>a</word><word>and</word><word>and</word><word>At</word><word>combination</word><word>customer,</word><word>expertise</word><word>financial</word><word>for</word><word>gives</word><word>global</word><word>have</word><word>local</word><word>Markets</word><word>Nordea</word><word>of</word><word>of</word><word>opportunity</word><word>our</word><word>rare</word><word>services</word><word>solutions</word><word>strength</word><word>the</word><word>to</word><word>us</word><word>use</word><word>variety</word><word>we</word><word>which</word><word>wide</word><word>you,</word></sentence>\n" +
                "  <sentence><word>(and</word><word>-</word><word>a</word><word>a</word><word>all</word><word>and</word><word>as</word><word>as</word><word>can</word><word>currencies</word><word>diversified</word><word>excellent</word><word>fact</word><word>finding</word><word>give</word><word>hard</word><word>have</word><word>In</word><word>in</word><word>in</word><word>liquidity</word><word>local</word><word>Nordics,</word><word>of</word><word>ours</word><word>product</word><word>range</word><word>strong</word><word>the</word><word>time</word><word>too)</word><word>we</word><word>you</word><word>you’d</word></sentence>\n" +
                "  <sentence><word>a</word><word>be</word><word>But</word><word>challenge</word><word>financial</word><word>have</word><word>huge</word><word>importantly,</word><word>matter</word><word>might</word><word>most</word><word>no</word><word>of</word><word>outstanding</word><word>ready</word><word>serve</word><word>specialists</word><word>team</word><word>to</word><word>we</word><word>what</word><word>you,</word><word>your</word></sentence>\n" +
                "</text>";

        // Assert that the actual output matches the expected output
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void test_processAndOutput_throwsIllegalArgumentException() {
        final String text = null;

        XMLOutputStrategy strategy = new XMLOutputStrategy();

        assertThrows(IllegalArgumentException.class, () -> {
            strategy.processAndOutput(text);
        });
    }
}
