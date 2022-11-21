package com.event;

import com.pointsystem.JumpEventPointSystem;

public class LongJump extends Event {
    public LongJump(String name) {
        super(1, name);
        this.setPointSystem(new JumpEventPointSystem(new double[]{0.14354, 220, 1.4}));
    }
}
