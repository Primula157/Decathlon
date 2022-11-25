package com.event;

import com.pointsystem.TrackEventPointSystem;

public class FourHundredMetres extends TrackEvent {
    public FourHundredMetres(String name) {
        super(4, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{1.53775, 82, 1.81}));
    }
}
