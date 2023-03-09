package com;

import com.decathlon.Decathlon;
import com.decathlon.FileParser;
import com.decathlon.athlete.Athlete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {
    private String inputFileName = "src\\test\\resources\\" + "test_results.csv";
    private String outputFileName = "src\\test\\resources\\" + "actual_test_data.xml";
    private Decathlon decathlon = new Decathlon();
    private FileParser fileParser;

    @BeforeEach
    public void initialize() throws IOException {
        fileParser = new FileParser(inputFileName, outputFileName);
    }

    @Test
    void getCompetitionResults() throws IOException, JAXBException {
        decathlon.calculateTheResultsOfCompetition(fileParser);
        Map<Athlete, Double[]> expectedResult = new HashMap<>();
        expectedResult.put(new Athlete("Yuumi"), new Double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60, 2.60, 35.81, 325.72});
        expectedResult.put(new Athlete("Yuuki"), new Double[]{13.04, 4.53, 7.79, 1.55, 64.72, 18.74, 24.20, 2.40, 28.20, 410.76});
        expectedResult.put(new Athlete("Dudon"), new Double[]{13.75, 4.84, 10.12, 1.50, 68.44, 19.18, 30.85, 2.80, 33.88, 382.75});
        expectedResult.put(new Athlete("Dudon2"), new Double[]{13.75, 4.84, 10.12, 1.50, 68.44, 19.18, 30.85, 2.80, 33.88, 382.75});
        Map<Athlete, Double[]> actualResult = decathlon.getPerformancesByTheAthletes();
        assertArrayEquals(expectedResult.keySet().toArray(), actualResult.keySet().toArray());
        assertArrayEquals(expectedResult.values().toArray(), actualResult.values().toArray());
    }

    @Test
    void getPerformanceByTheAthlete() {
        try {
            Method privateMethod = FileParser.class.getDeclaredMethod("getPerformanceByTheAthlete", String.class, boolean.class);
            privateMethod.setAccessible(true);
            assertEquals(14.5d, privateMethod.invoke(fileParser, "14.5", false));
            assertEquals(438.08d, privateMethod.invoke(fileParser, "7:18.8", true));
            assertEquals(59d, privateMethod.invoke(fileParser, "59", true));
            assertEquals(0d, privateMethod.invoke(fileParser, "sdf", true));
            assertEquals(0d, privateMethod.invoke(fileParser, "-47", true));
            assertEquals(0, privateMethod.invoke(fileParser, "-7:18.8", true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSeconds() {
        try {
            Method privateMethod = FileParser.class.getDeclaredMethod("getSeconds", String.class);
            privateMethod.setAccessible(true);
            assertEquals(180d, privateMethod.invoke(fileParser, "03:00.00"));
            assertEquals(0d, privateMethod.invoke(fileParser, "00:00.00"));
            assertEquals(0d, privateMethod.invoke(fileParser, "ds"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void readFile() {
        String expectedFileData = "Yuumi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72 \n" +
                "Yuuki;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76 \n" +
                "Dudon;13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6:22.75\n" +
                "Dudon2;13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6:22.75";
        try {
            Method privateMethod = FileParser.class.getDeclaredMethod("readFile", null);
            privateMethod.setAccessible(true);
            assertEquals(expectedFileData, privateMethod.invoke(fileParser));
            assertEquals("", privateMethod.invoke(fileParser));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveDataToFile() {
        try {
            decathlon.calculateTheResultsOfCompetition(fileParser);
            fileParser.saveDataToFile(decathlon.getScoreTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String expectedFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + "expected_test_data.xml";
        try (InputStreamReader actualFileReader = new InputStreamReader(new FileInputStream(outputFileName));
        InputStreamReader excpectedFileReader = new InputStreamReader(new FileInputStream(expectedFilePath))) {
            while(actualFileReader.ready()) {
                assertEquals(actualFileReader.read(), excpectedFileReader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}