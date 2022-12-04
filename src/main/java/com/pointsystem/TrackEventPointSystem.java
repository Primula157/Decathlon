package com.pointsystem;

public class TrackEventPointSystem extends PointSystem {
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
