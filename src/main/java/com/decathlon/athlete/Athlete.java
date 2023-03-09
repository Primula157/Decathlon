package com.decathlon.athlete;

public class Athlete {
    private String name;

    public Athlete(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object athlete){
        if(athlete instanceof Athlete)
            return ((Athlete) athlete).getName().equals(this.getName());
        return false;
    }

    public int hashCode(){
        return name.length();
    }

    public String toString(){
        return this.name;
    }
}
