package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.EventFactory;
import com.pointsystem.Unit;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Locale;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalTime;

public class Decathlon {

    private static final List<Event> events;
    private Map<Athlete, Double[]> performancesByTheAthletes;
    private ScoreTable scoreTable;

    public static List<Event> getEvents() {
        return events;
    }

    public Map<Athlete, Double[]> getPerformancesByTheAthletes() {
        return performancesByTheAthletes;
    }

    public ScoreTable getScoreTable() {
        return scoreTable;
    }

    static {
        events = createEventsList();
    }

    private static List<Event> createEventsList() {
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

    private ScoreTable loadScoreTable() {
        this.scoreTable = new ScoreTable();

        for (Map.Entry<Athlete, Double[]> performanceByTheAthlete : performancesByTheAthletes.entrySet()) {
            Athlete athlete = performanceByTheAthlete.getKey();
            Double[] competitionsResults = performanceByTheAthlete.getValue();
            Map<Event, String> performances = new HashMap<>();
            int totalScore = loadPerformancesAndGetTotalScore(competitionsResults, performances);
            scoreTable.add(athlete, performances, totalScore);
        }

        return scoreTable;
    }

    private int loadPerformancesAndGetTotalScore(Double[] competitionsResults, Map<Event, String> performances) {
        int totalScore = 0;

        for (int i = 0; i < competitionsResults.length; i++) {
            Event event = events.get(i);
            if (event.getPointSystem().getUnit() == Unit.MINUTES_AND_SECONDS) {
                LocalTime localTime = LocalTime.ofSecondOfDay(competitionsResults[i].longValue());
                int minutes = localTime.getMinute();
                int seconds = localTime.getSecond();
                int milliseconds = Integer.parseInt(String.valueOf(competitionsResults[i]).split("[.,]")[1]);
                String time = String.format("%d:%02d.%02d", minutes, seconds, milliseconds);
                performances.put(event, time);
            } else {
                performances.put(event, String.format(Locale.ENGLISH, "%.2f", competitionsResults[i]));
            }
            totalScore += event.getPointSystem().calculatePoints(competitionsResults[i]);
        }
        return totalScore;
    }

    public void calculateTheResultsOfCompetition(FileParser fileParser) throws IOException, JAXBException {
        performancesByTheAthletes = fileParser.getCompetitionResults();
        scoreTable = loadScoreTable();
        scoreTable.sort();
        fileParser.saveDataToFile(scoreTable);
    }
}
