package com;

import com.athlete.Athlete;
import com.event.Event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "tableScore")
public class ScoreTable {

    @XmlElement(name = "row")
    private List<Row> rows = new ArrayList<>();
    public List<Row> getRows() {
        return rows;
    }

    public void add(Athlete athlete, Map<Event, String> performances, int totalScore) {
        Row row = new Row(athlete, performances, totalScore);
        row.setPlace(String.valueOf(0));
        rows.add(row);
    }

    // сортировка таблицы результатов соревнований по общему количеству очков и определение места спортсмена в рейтинге
    public void sort() {
        rows.sort(Collections.reverseOrder(Comparator.comparingInt(x -> x.totalScore)));
        for (int i = 0; i < rows.size(); i++) {
            Row currentAthlete = rows.get(i);
            if (i + 1 < rows.size()) {
                Row nextAthlete = rows.get(i + 1);
                if (currentAthlete.totalScore == nextAthlete.totalScore) {
                    currentAthlete.setPlace(String.format("%d-%d", i + 1, i + 2));
                    nextAthlete.setPlace(String.format("%d-%d", i + 1, i + 2));
                    i += 1;
                    continue;
                }
            }
            currentAthlete.setPlace(String.valueOf(i + 1));
        }
    }

    // подкласс "Строка" содержит информацию об одном спортсмене и его результатах в соревновании
    public static class Row {
        @XmlElement(name = "place")
        private String place;

        @XmlElement(name = "athlete")
        private Athlete athlete;

        @XmlElement(name = "performances")
        private Map<Event, String> performances;

        @XmlElement(name = "totalScore")
        private int totalScore;

        public String getPlace() {
            return place;
        }

        public Athlete getAthlete() {
            return athlete;
        }

        public Map<Event, String> getPerformances() {
            return performances;
        }

        public int getTotalScore() {
            return totalScore;
        }

        private Row(Athlete athlete, Map<Event, String> performances, int totalScore) {
            this.athlete = athlete;
            this.performances = performances;
            this.totalScore = totalScore;
        }

        private void setPlace(String place) {
            this.place = place;
        }
    }
}
