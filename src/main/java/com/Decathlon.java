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
        int id = 0;
        List<Event> result = new ArrayList<>();
        Collections.addAll(result, EventFactory.createEvent(id++,"100 m"),
                EventFactory.createEvent(id++,"Long jump"),
                EventFactory.createEvent(id++,"Shot put"),
                EventFactory.createEvent(id++,"High jump"),
                EventFactory.createEvent(id++,"400 m"),
                EventFactory.createEvent(id++,"110 m hurdles"),
                EventFactory.createEvent(id++,"Discus throw"),
                EventFactory.createEvent(id++,"Pole vault"),
                EventFactory.createEvent(id++,"Javelin throw"),
                EventFactory.createEvent(id,"1500 m"));
        return result;
    }
}
