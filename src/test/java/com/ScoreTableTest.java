package com;

import com.decathlon.Decathlon;
import com.decathlon.FileParser;
import com.decathlon.ScoreTable;
import com.decathlon.athlete.Athlete;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

class ScoreTableTest {
    private static final Decathlon decathlon = new Decathlon();

    @BeforeAll
    public static void initialize() throws IOException, JAXBException {
        String inputFileName = "\\src\\test\\resources\\" + "test_results.csv";
        String outputFileName = "\\src\\test\\resources\\" + "actual_test_data.xml";
        FileParser fileParser = new FileParser(inputFileName, outputFileName);
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