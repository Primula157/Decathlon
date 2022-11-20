package com.pointsystem;

public abstract class PointSystem {
    protected double[] params;

    public abstract int calculatePoints(double performanceByTheAthlete);

    public PointSystem(double[] params) {
        this.params = params;
    }
}
