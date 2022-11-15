package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.EventFactory;
import com.event.FieldEvent;
import com.event.TrackEvent;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Map<Athlete, Map<Event, Double>> hashMap = getCompetitionResults( "E:\\Java\\results.csv");
        for(Map.Entry<Athlete, Map<Event, Double>> line : hashMap.entrySet()) {
            System.out.println(line.getKey().getName());
            for(Map.Entry<Event, Double> results : line.getValue().entrySet()) {
                System.out.println(results.getKey().getName() + " " + results.getValue());
            }
        }
    }

    public static Map<Athlete, Map<Event, Double>> getCompetitionResults(String path){
        String competitionResults = readFile(path);
        String[] lines = competitionResults.split("\n");
        Map<Event, Double> map = new HashMap<>();
        Map<Athlete, Map<Event, Double>> result = new HashMap<>();
        List<Event> events = Decathlon.createEventsList();

        for (int i = 0; i < lines.length - 1; i++) {
            String[] line = lines[i].split(";");
            Athlete athlete = new Athlete(line[0]);
            for (int j = 1; j < line.length - 1; j++) {
                map.put(events.get(j-1), Double.valueOf(line[j]));
            }
            result.put(athlete, map);
        }
        return result;
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
