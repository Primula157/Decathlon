package com;

import com.athlete.Athlete;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


class ScoreTableTest {
    private static String inputFileName = "test_results.csv";
    private static String outputFileName = "actual_test_data.xml";
    private static Decathlon decathlon = new Decathlon();
    private static FileParser fileParser;

    @BeforeAll
    public static void initialize() throws IOException, JAXBException {
        fileParser = new FileParser(inputFileName, outputFileName);
        decathlon.calculateTheResultsOfCompetition(fileParser);
    }

    @Test
    void sort(){
        ScoreTable scoreTable = decathlon.getScoreTable();
        List<ScoreTable.Row> rows = scoreTable.getRows();
        for(ScoreTable.Row row : rows) {
            Athlete currentAthlete = row.getAthlete();
            if (currentAthlete.getName().equals("Dudon2")) {
                assertEquals(row.getPlace(), "2-3");
            }
        }
    }
}