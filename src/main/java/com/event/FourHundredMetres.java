package com.event;

import com.pointsystem.TrackEventPointSystem;
import com.pointsystem.Unit;

public class FourHundredMetres extends TrackEvent {
    public FourHundredMetres(String name) {
        super(4, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{1.53775, 82, 1.81}, Unit.SECONDS));
    }
}
