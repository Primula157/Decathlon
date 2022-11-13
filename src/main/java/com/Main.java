package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.FieldEvent;
import com.event.TrackEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args){
        getCompetitionResults("C:\\Users\\Primula\\Documents\\NetBeansProjects\\Decathlon\\results.csv");
    }

    public static List<Athlete> getCompetitionResults(String path){

        return null;
    }

    public static Athlete parseLine(String line){
        String[] results = line.split(";");
        for(int i = 0; i < results.length; i++) {
            System.out.printf("%s ", results[i]);
        }
        System.out.println();
        return null;
    }

    public static void readFile(String path){
        List<Athlete> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while(reader.ready()) {
                Athlete athlete = parseLine(reader.readLine());
                result.add(athlete);
            }
        } catch (IOException e) {

        }
    }

    public static List<Event> createEventsList(){
        List<Event> result = new ArrayList<>();
        Collections.addAll(result, new TrackEvent("100 m"),
                new FieldEvent("Long jump"),
                new FieldEvent("Shot put"),
                new FieldEvent("High jump"),
                new TrackEvent("400 m"),
                new TrackEvent("110 m hurdles"),
                new FieldEvent("Discus throw"),
                new FieldEvent("Pole vault"),
                new FieldEvent("Javelin throw"),
                new TrackEvent("1500 m"));
        return result;
    }
}
