package com;

import com.athlete.Athlete;
import com.event.Event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "tableScore")
public class ScoreTable {
    @XmlElement(name = "row")
    private static List<Row> rows = new ArrayList<>();

    private static ScoreTable instance;

    public static ScoreTable getInstance() {
        if (instance == null) {
            instance = new ScoreTable();
        }
        return instance;
    }

    private ScoreTable() {
    }

    public void add(Athlete athlete, Map<Event, String> performances, int totalScore) {
        Row row = new Row(athlete, performances, totalScore);
        row.setPlace(0);
        rows.add(row);
    }

    public void sort() {
        rows.sort(Collections.reverseOrder(Comparator.comparingInt(x -> x.totalScore)));
        int i = 1;
        for (Row row : rows) {
            row.setPlace(i++);
        }
    }

    public void printScoreTable() {
        for (Row row : rows) {
            System.out.printf("%d %s %d\n", row.place, row.athlete.getName(), row.totalScore);
            Map<Event, String> performances = row.performances;
            for (Map.Entry<Event, String> performance : performances.entrySet()) {
                System.out.printf("%s %f\n", performance.getKey(), performance.getValue());
            }
        }
    }


    private static class Row {
        @XmlElement(name = "place")
        private int place;

        @XmlElement(name = "athlete")
        private Athlete athlete;

        @XmlElement(name = "performances")
        private Map<Event, String> performances;

        @XmlElement(name = "totalScore")
        private int totalScore;

        private Row(Athlete athlete, Map<Event, String> performances, int totalScore) {
            this.athlete = athlete;
            this.performances = performances;
            this.totalScore = totalScore;
        }

        private void setPlace(int place) {
            this.place = place;
        }
    }
}
