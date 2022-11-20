package com.event;

import com.pointsystem.FieldEventPointSystem;

public class JavelinThrow extends Event {
    public JavelinThrow(String name) {
        super(8, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{10.14, 7, 1.08}));
    }
}
