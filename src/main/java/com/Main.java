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
        Map<Athlete, Double[]> competitionResults = getCompetitionResults( "E:\\Java\\results.csv");
        for(Map.Entry<Athlete, Double[]> line : competitionResults.entrySet()) {
            System.out.print(line.getKey().getName() + " - ");
            for (int i = 0; i < line.getValue().length; i++) {
                System.out.printf("%f ", line.getValue()[i]);
            }
            System.out.println();
        }
    }

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
                if (j == line.length - 1) {
                    String[] time = line[j].split("[:. ]");
                    LocalTime localTime = LocalTime.of(0, Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
                    allPerformancesByAthlete[j - 1] = (double)localTime.toNanoOfDay();
                    System.out.println(localTime.getSecond());
                } else {
                    allPerformancesByAthlete[j - 1] = Double.valueOf(line[j]);
                }
            }
            competitionResults.put(athlete, allPerformancesByAthlete);
        }
        return competitionResults;
    }

    public static String readFile(String path){
        StringBuilder result = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while(reader.ready()) {
                String line = reader.readLine();
                result.append(line).append("\n");
            }
        } catch (IOException e) { }
        return result.toString();
    }
}
