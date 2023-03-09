package com.decathlon.event;

import com.decathlon.pointsystem.FieldEventPointSystem;
import com.decathlon.pointsystem.Unit;

public class JavelinThrow extends FieldEvent {
    public JavelinThrow(String name) {
        super(8, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{10.14, 7, 1.08}, Unit.METRES));
    }
}
