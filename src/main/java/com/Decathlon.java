package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.EventFactory;
import com.event.OneThousandFiveHundredMetres;
import com.event.TrackEvent;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Decathlon {
    private static List<Event> events;
    private static Map<Athlete, Double[]> performancesByTheAthletes;
    private static ScoreTable scoreTable;

    static {
        events = createEventsList();
    }

    private Decathlon() {
    }

    public static List<Event> createEventsList() {
        List<Event> result = new ArrayList<>();
        Collections.addAll(result, EventFactory.createEvent("100 m"),
                EventFactory.createEvent("Long jump"),
                EventFactory.createEvent("Shot put"),
                EventFactory.createEvent("High jump"),
                EventFactory.createEvent("400 m"),
                EventFactory.createEvent("110 m hurdles"),
                EventFactory.createEvent("Discus throw"),
                EventFactory.createEvent("Pole vault"),
                EventFactory.createEvent("Javelin throw"),
                EventFactory.createEvent("1500 m"));
        return result;
    }

    public static ScoreTable loadScoreTable() {
        ScoreTable scoreTable = ScoreTable.getInstance();

        for (Map.Entry<Athlete, Double[]> performanceByTheAthlete : performancesByTheAthletes.entrySet()) {
            Athlete athlete = performanceByTheAthlete.getKey();
            Double[] competitionsResults = performanceByTheAthlete.getValue();
            Map<Event, String> performances = new HashMap<>();
            int totalScore = 0;
            for (int i = 0; i < competitionsResults.length; i++) {
                Event event = events.get(i);
                if (event instanceof TrackEvent) {
                    LocalTime localTime = LocalTime.ofSecondOfDay(competitionsResults[i].longValue());
                    //int miliseconds = competitionsResults[i];
                    //localTime.plusNanos();
                    performances.put(event, localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
                } else {
                    performances.put(event, String.valueOf(competitionsResults[i]));
                }
                totalScore += event.getPointSystem().calculatePoints(competitionsResults[i]);
            }
            scoreTable.add(athlete, performances, totalScore);
        }

        return scoreTable;
    }

    public static void main(String[] args) {
        String inputFileName = "results.csv";
        String path = System.getProperty("user.dir");
        performancesByTheAthletes = FileParser.getCompetitionResults(path + "\\" + inputFileName);
        scoreTable = loadScoreTable();
        scoreTable.sort();

        String outputFileName = "data.xml";
        File file = new File(path + "\\" + outputFileName);
        FileParser.saveDataToFile(file);
    }
}
