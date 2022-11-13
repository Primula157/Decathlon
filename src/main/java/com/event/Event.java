package com.event;

public abstract class Event {
    private String name;

    public Event(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    private static class Params {
        private double value[] = new double[3];

        public void setValue(){

        }
    }
}
