package com.event;

import com.pointsystem.FieldEventPointSystem;

public class LongJump extends Event {
    public LongJump(String name) {
        super(1, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{0.14354, 220, 1.4}));
    }
}
