package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.EventFactory;
import com.event.FieldEvent;
import com.event.TrackEvent;

import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args){
        //TODO: добавить возможность читать из файла который лежит в папке с проектом
        Map<Athlete, Double[]> competitionResults = getCompetitionResults( "E:\\Java\\results.csv");
    }

    // обработка содержимого файла
    public static Map<Athlete, Double[]> getCompetitionResults(String path){
        String allCompetitionResultsFromFile = readFile(path);
        String[] competitionResultsLineByLine = allCompetitionResultsFromFile.split("\n");
        Map<Athlete, Double[]> competitionResults = new HashMap<>();

        for (int i = 0; i < competitionResultsLineByLine.length; i++) {
            String[] line = competitionResultsLineByLine[i].split(";");
            String athleteName = line[0];
            Athlete athlete = new Athlete(athleteName);
            Double[] allPerformancesByAthlete = new Double[line.length - 1];
            for (int j = 1; j < line.length; j++) {
                addPerformances(line[j], j == line.length - 1);
            }
            competitionResults.put(athlete, allPerformancesByAthlete);
        }
        return competitionResults;
    }

    private static Double addPerformances(String line, boolean isLastElement) {
        if (isLastElement) {
            String[] time = line.split("[:. ]");
            int minutes = Integer.parseInt(time[0]);
            int seconds = Integer.parseInt(time[1]);
            int nanoSeconds = Integer.parseInt(time[2]);
            LocalTime localTime = LocalTime.of
                    (0, minutes, seconds, nanoSeconds);
            return (double)localTime.toNanoOfDay();
        } else {
            return Double.valueOf(line);
        }
    }

    public static String readFile(String path){
        StringBuilder result = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while(reader.ready()) {
                String line = reader.readLine();
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
