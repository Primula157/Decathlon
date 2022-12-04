package com.event;

import com.pointsystem.FieldEventPointSystem;
import com.pointsystem.Unit;

public class ShotPut extends TrackEvent {
    public ShotPut(String name) {
        super(2, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{51.39, 1.5, 1.05}, Unit.SECONDS));
    }
}
