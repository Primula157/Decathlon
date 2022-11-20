package com.event;

import com.pointsystem.TrackEventPointSystem;

public class OneThousandFiveHundredMetres extends Event {
    public OneThousandFiveHundredMetres(String name) {
        super(9, name);
        this.setPointSystem(new TrackEventPointSystem(new double[]{0.03768, 480, 1.85}));
    }
}
