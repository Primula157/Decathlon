package com.event;

import com.pointsystem.FieldEventPointSystem;

public class HighJump extends Event {
    public HighJump(String name) {
        super(3, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{0.8465, 75, 1.42}));
    }
}