package com.decathlon.event;

import com.decathlon.pointsystem.TrackEventPointSystem;
import com.decathlon.pointsystem.Unit;

public class HundredMetres extends TrackEvent {
    public HundredMetres(String name) {
        super(0, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{25.4347, 18, 1.81}, Unit.SECONDS));
    }
}
