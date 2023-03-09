package com.decathlon.event;

import com.decathlon.pointsystem.FieldEventPointSystem;
import com.decathlon.pointsystem.Unit;

public class HighJump extends FieldEvent {
    public HighJump(String name) {
        super(3, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{0.8465, 75, 1.42}, Unit.CENTIMETRES));
    }
}
