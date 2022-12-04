package com.event;

import com.pointsystem.FieldEventPointSystem;
import com.pointsystem.Unit;

public class PoleVault extends FieldEvent {
    public PoleVault(String name) {
        super(7, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{0.2797, 100, 1.35}, Unit.CENTIMETRES));
    }
}
