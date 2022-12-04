package com.event;

import com.pointsystem.FieldEventPointSystem;
import com.pointsystem.Unit;

public class JavelinThrow extends FieldEvent {
    public JavelinThrow(String name) {
        super(8, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{10.14, 7, 1.08}, Unit.METRES));
    }
}
