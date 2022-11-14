package com.event;

public abstract class Event {
    private String name;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private class PointSystem {
        private double params[];

        private PointSystem(){
        }

        public void initialize(){
            switch (name) {
                case "100m":
                    params = new double[]{25.4347, 18, 1.81};
                    break;
                case "400 m":
                    params = new double[]{1.53775, 82, 1.81};
                    break;
                case "110 m hurdles":
                    params = new double[]{5.74325, 28.5, 1.92};
                    break;
                case "1500 m":
                    params = new double[]{0.03768, 480, 1.85};
                    break;
                case "Long jump":
                    params = new double[]{0.14354, 220, 1.4};
                    break;
                case "Shot put":
                    params = new double[]{51.39, 1.5, 1.05};
                    break;
                case "High jump":
                    params = new double[]{0.8465, 75, 1.42};
                    break;
                case "Discus throw":
                    params = new double[]{12.91, 4, 1.1};
                    break;
                case "Pole vault":
                    params = new double[]{0.2797, 100, 1.35};
                    break;
                case "Javelin throw":
                    params = new double[]{10.14, 7, 1.08};
                    break;
            }
        }

        public double[] getParams(){
            return params;
        }
    }
}
