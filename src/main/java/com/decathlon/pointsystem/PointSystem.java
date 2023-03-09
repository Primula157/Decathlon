package com.decathlon.pointsystem;

public abstract class PointSystem {
    protected double[] params;
    protected Unit unit;

    public abstract int calculatePoints(double performanceByTheAthlete);

    public PointSystem(double[] params, com.decathlon.pointsystem.Unit unit) {
        this.params = params;
        this.unit = unit;
    }

    public com.decathlon.pointsystem.Unit getUnit(){
        return unit;
    }
}
