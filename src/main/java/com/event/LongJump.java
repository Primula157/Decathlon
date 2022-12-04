package com.event;

import com.pointsystem.FieldEventPointSystem;
import com.pointsystem.Unit;

public class LongJump extends FieldEvent {
    public LongJump(String name) {
        super(1, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{0.14354, 220, 1.4}, Unit.CENTIMETRES));
    }
}
