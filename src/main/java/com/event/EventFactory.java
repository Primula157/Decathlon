package com.event;

public class EventFactory {
    private EventFactory() {
    }

    public static Event createEvent(int id, String name) {
        Event event = null;

        switch (name) {
            case "100 m":
            case "400 m":
            case "110 m hurdles":
            case "1500 m":
                event = new TrackEvent(id, name);
                break;
            case "Long jump":
            case "Shot put":
            case "High jump":
            case "Discus throw":
            case "Pole vault":
            case "Javelin throw":
                event = new FieldEvent(id, name);
                break;
        }

        return event;
    }
}
