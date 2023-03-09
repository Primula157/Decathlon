package com.decathlon.event;

import com.decathlon.pointsystem.PointSystem;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public abstract class Event {
    private final int id;

    @XmlAttribute
    private String name;

    @XmlTransient
    private PointSystem pointSystem;

    public Event(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @XmlTransient
    public PointSystem getPointSystem() {
        return pointSystem;
    }

    public void setPointSystem(PointSystem pointSystem) {
        this.pointSystem = pointSystem;
    }

    public int hashCode() {
        return id;
    }

    public boolean equals(Object event) {
        if (event instanceof Event) {
            return this.id == ((Event) event).id;
        }
        return false;
    }
}
