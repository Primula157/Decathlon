package com.event;

import com.pointsystem.TrackEventPointSystem;
import com.pointsystem.Unit;

public class OneHundredTenMetresHurdles extends TrackEvent {
    public OneHundredTenMetresHurdles(String name) {
        super(5, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{5.74325, 28.5, 1.92}, Unit.SECONDS));
    }
}
