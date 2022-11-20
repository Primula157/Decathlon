package com.pointsystem;

public class FieldEventPointSystem extends PointSystem {
    public FieldEventPointSystem(double[] params) {
        super(params);
    }

    public int calculatePoints(double performanceByTheAthlete) {
        double result = 0;
        double A = params[0];
        double B = params[1];
        double C = params[2];

        result = Math.abs(A * Math.pow((performanceByTheAthlete - B), C));

        return (int) result;
    }
}