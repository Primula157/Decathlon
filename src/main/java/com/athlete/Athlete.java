package com.athlete;

public class Athlete {
    private String name;
    private int totalScore;

    public Athlete(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getTotalScore(){
        return totalScore;
    }

    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }
}
