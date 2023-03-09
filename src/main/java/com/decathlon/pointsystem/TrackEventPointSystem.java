package com.decathlon.pointsystem;

public class TrackEventPointSystem extends com.decathlon.pointsystem.PointSystem {
    public TrackEventPointSystem(double[] params, Unit unit) {
        super(params, unit);
    }

    public int calculatePoints(double performanceByTheAthlete) {
        double result = 0;
        double A = params[0];
        double B = params[1];
        double C = params[2];

        result = Math.abs(A * Math.pow((B - performanceByTheAthlete), C));

        return (int) result;
    }
}
