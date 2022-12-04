package com.event;

import com.pointsystem.FieldEventPointSystem;
import com.pointsystem.Unit;

public class DiscusThrow extends FieldEvent {
    public DiscusThrow(String name) {
        super(6, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{12.91, 4, 1.1}, Unit.METRES));
    }
}
