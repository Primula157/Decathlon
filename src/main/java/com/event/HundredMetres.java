package com.event;

import com.pointsystem.TrackEventPointSystem;

public class HundredMetres extends Event {
    public HundredMetres(String name) {
        super(0, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{25.4347, 18, 1.81}));
    }
}
