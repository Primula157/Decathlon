package com.decathlon.event;

import com.decathlon.pointsystem.TrackEventPointSystem;
import com.decathlon.pointsystem.Unit;

public class OneHundredTenMetresHurdles extends TrackEvent {
    public OneHundredTenMetresHurdles(String name) {
        super(5, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{5.74325, 28.5, 1.92}, Unit.SECONDS));
    }
}
