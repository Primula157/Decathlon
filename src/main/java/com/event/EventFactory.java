package com.event;

public class EventFactory {
    private EventFactory() {
    }

    public static Event createEvent(String name) {
        Event event = null;

        switch (name) {
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
            case "Long jump":
                event = new LongJump(name);
                break;
            case "Shot put":
                event = new ShotPut(name);
                break;
            case "High jump":
                event = new HighJump(name);
                break;
            case "Discus throw":
                event = new DiscusThrow(name);
                break;
            case "Pole vault":
                event = new PoleVault(name);
                break;
            case "Javelin throw":
                event = new JavelinThrow(name);
                break;
        }

        return event;
    }
}
