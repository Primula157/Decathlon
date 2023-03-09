package com.decathlon.event;

import com.decathlon.pointsystem.FieldEventPointSystem;
import com.decathlon.pointsystem.Unit;

public class ShotPut extends TrackEvent {
    public ShotPut(String name) {
        super(2, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{51.39, 1.5, 1.05}, Unit.SECONDS));
    }
}
