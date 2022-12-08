package com.event.factory;

import com.event.*;

public class EventFactory {
    private EventFactory() {
    }

    public static Event createEvent(String name) {
        Event event = null;

        switch (name.toLowerCase()) {
            case "100 m":
                event = new HundredMetres(name);
                break;
            case "400 m":
                event = new FourHundredMetres(name);
                break;
            case "110 m hurdles":
                event = new OneHundredTenMetresHurdles(name);
                break;
            case "1500 m":
                event = new OneThousandFiveHundredMetres(name);
                break;
            case "long jump":
                event = new LongJump(name);
                break;
            case "shot put":
                event = new ShotPut(name);
                break;
            case "high jump":
                event = new HighJump(name);
                break;
            case "discus throw":
                event = new DiscusThrow(name);
                break;
            case "pole vault":
                event = new PoleVault(name);
                break;
            case "javelin throw":
                event = new JavelinThrow(name);
                break;
            default:
                throw new RuntimeException("Illegal event name");
        }

        return event;
    }
}
