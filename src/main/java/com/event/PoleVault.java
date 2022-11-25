package com.event;

import com.pointsystem.JumpEventPointSystem;

public class PoleVault extends FieldEvent {
    public PoleVault(String name) {
        super(7, name);
        this.setPointSystem(new JumpEventPointSystem(new double[]{0.2797, 100, 1.35}));
    }
}
