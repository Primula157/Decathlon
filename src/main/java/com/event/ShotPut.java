package com.event;

import com.pointsystem.FieldEventPointSystem;

public class ShotPut extends TrackEvent {
    public ShotPut(String name) {
        super(2, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{51.39, 1.5, 1.05}));
    }
}
