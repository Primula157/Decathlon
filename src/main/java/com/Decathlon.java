package com;

import com.athlete.Athlete;
import com.event.Event;
import com.event.EventFactory;
import com.event.FieldEvent;

import java.util.*;

public class Decathlon {
    private List<Athlete> athletes;
    private List<Event> events;
    private HashMap<Athlete, Map> performancesByTheAthletes;

    public Decathlon(){
        events = createEventsList();
    }

    public static List<Event> createEventsList(){
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
}
