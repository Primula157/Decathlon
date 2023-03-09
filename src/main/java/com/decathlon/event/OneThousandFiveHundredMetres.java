package com.decathlon.event;

import com.decathlon.pointsystem.TrackEventPointSystem;
import com.decathlon.pointsystem.Unit;

public class OneThousandFiveHundredMetres extends TrackEvent {
    public OneThousandFiveHundredMetres(String name) {
        super(9, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{0.03768, 480, 1.85}, Unit.MINUTES_AND_SECONDS));
    }
}
