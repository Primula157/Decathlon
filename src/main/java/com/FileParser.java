package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.TrackEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class FileParser {
    // обработка содержимого файла
    public static Map<Athlete, Double[]> getCompetitionResults(String path) {
        String allCompetitionResultsFromFile = readFile(path);
        String[] competitionResultsLineByLine = allCompetitionResultsFromFile.split("\n");
        Map<Athlete, Double[]> competitionResults = new HashMap<>();

        for (int i = 0; i < competitionResultsLineByLine.length; i++) {
            String[] line = competitionResultsLineByLine[i].split(";");
            String athleteName = line[0];
            Athlete athlete = new Athlete(athleteName);
            Double[] allPerformancesByAthlete = new Double[line.length - 1];
            List<Event> events = Decathlon.createEventsList();
            for (int j = 1; j < line.length; j++) {
                allPerformancesByAthlete[j - 1] = addPerformances(line[j], events.get(j - 1) instanceof TrackEvent);
            }
            competitionResults.put(athlete, allPerformancesByAthlete);
        }
        return competitionResults;
    }

    private static Double addPerformances(String performanceByTheAthlete, boolean isTrackEvent) { // addPerformances это уместное название метода?
        Double result = 0d;

        try {
            result = Double.parseDouble(performanceByTheAthlete);
        } catch (NumberFormatException e) {
            if (isTrackEvent) {
                result = getSeconds(performanceByTheAthlete);
            } else {
                e.printStackTrace();
            }
        }

        return result;
    }

    private static Double getSeconds(String performanceByTheAthlete) {
        String[] time = performanceByTheAthlete.split("[:. ]");
        int nanoSeconds = Integer.parseInt(time[time.length - 1]);
        int seconds = time.length - 2 >= 0 ? Integer.parseInt(time[time.length - 2]) : 0;
        int minutes = time.length - 3 >= 0 ? Integer.parseInt(time[time.length - 3]) : 0;
        LocalTime localTime = LocalTime.of
                (0, minutes, seconds, nanoSeconds);
        return (double) localTime.toSecondOfDay() + (localTime.getNano() * 0.01);
    }

    public static String readFile(String path) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String line = reader.readLine();
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void saveDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ScoreTable.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ScoreTable scoreTable = ScoreTable.getInstance();
            marshaller.marshal(scoreTable, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
