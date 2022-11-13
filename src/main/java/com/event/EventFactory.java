package com.event;

public class EventFactory {
    private EventFactory() { }

    public static void createEvent(String name){
        double[] params;
        switch(name) {
            case "100m":
                new TrackEvent(name);
                params = new double[] { };
        }
    }
}
