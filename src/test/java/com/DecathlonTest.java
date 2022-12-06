package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.EventFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DecathlonTest {
    private static final Decathlon decathlon = new Decathlon();

    @BeforeAll
    static void initialize(){
        try {
            String inputFileName = "test_results.csv";
            String outputFileName = "\\src\\main\\resources\\" + "actual_test_data.xml";
            FileParser fileParser = new FileParser(inputFileName, outputFileName);
            decathlon.calculateTheResultsOfCompetition(fileParser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadScoreTable() throws IOException, JAXBException {
        Athlete yuumi = new Athlete("Yuumi");
        Map<Event, String> performancesByYuumi = new HashMap<>();
        performancesByYuumi.put(EventFactory.createEvent("100 m"), "12.61");
        performancesByYuumi.put(EventFactory.createEvent("Long jump"), "5.00");
        performancesByYuumi.put(EventFactory.createEvent("Shot put"), "9.22");
        performancesByYuumi.put(EventFactory.createEvent("High jump"), "1.50");
        performancesByYuumi.put(EventFactory.createEvent("400 m"), "60.39");
        performancesByYuumi.put(EventFactory.createEvent("110 m hurdles"), "16.43");
        performancesByYuumi.put(EventFactory.createEvent("Discus throw"), "21.60");
        performancesByYuumi.put(EventFactory.createEvent("Pole vault"), "2.60");
        performancesByYuumi.put(EventFactory.createEvent("Javelin throw"), "35.81");
        performancesByYuumi.put(EventFactory.createEvent("1500 m"), "5:25.72");
        int yuumisTotalScore = 4200;

        ScoreTable.Row actualRow = decathlon.getScoreTable().getRows().get(0);
        Athlete actualAthlete = actualRow.getAthlete();
        assertEquals(yuumi, actualAthlete);
        assertEquals(yuumisTotalScore, actualRow.getTotalScore());

        Map<Event, String> actualPerformancesByTheAthlete = actualRow.getPerformances();
        for(Map.Entry<Event, String> performanceByYuumi : performancesByYuumi.entrySet()) {
            assertTrue(actualPerformancesByTheAthlete.containsKey(performanceByYuumi.getKey()));
            assertTrue(actualPerformancesByTheAthlete.containsValue(performanceByYuumi.getValue()));
        }

        for(Map.Entry<Event, String> actualPerformanceByTheAthlete: actualPerformancesByTheAthlete.entrySet()) {
            assertTrue(performancesByYuumi.containsKey(actualPerformanceByTheAthlete.getKey()));
            assertTrue(performancesByYuumi.containsValue(actualPerformanceByTheAthlete.getValue()));
        }
    }
}