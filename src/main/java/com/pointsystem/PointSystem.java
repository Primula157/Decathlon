package com.pointsystem;

public abstract class PointSystem {
    protected double[] params;
    protected Unit unit;

    public abstract int calculatePoints(double performanceByTheAthlete);

    public PointSystem(double[] params, Unit unit) {
        this.params = params;
        this.unit = unit;
    }

    public Unit getUnit(){
        return unit;
    }
}
