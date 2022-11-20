package com.event;

import com.pointsystem.FieldEventPointSystem;

public class DiscusThrow extends Event {
    public DiscusThrow(String name) {
        super(6, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{12.91, 4, 1.1}));
    }
}
