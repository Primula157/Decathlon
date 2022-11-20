package com.event;

import com.pointsystem.FieldEventPointSystem;

public class PoleVault extends Event {
    public PoleVault(String name) {
        super(7, name);
        this.setPointSystem(new FieldEventPointSystem(new double[]{0.2797, 100, 1.35}));
    }
}
