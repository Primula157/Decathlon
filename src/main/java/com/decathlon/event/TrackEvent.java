package com.decathlon.event;

public abstract class TrackEvent extends Event {
    public TrackEvent(int id, String name){
        super(id, name);
    }
}
