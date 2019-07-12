package org.dimigo.gui.javaproject;

import javafx.beans.property.SimpleStringProperty;


public class ExpressWay {
    private SimpleStringProperty name;
    private SimpleStringProperty subname;
    private SimpleStringProperty distance;
    private SimpleStringProperty speed;
    private SimpleStringProperty time;

    public ExpressWay(String name, String subname, String distance, String speed, String time) {
        this.name = new SimpleStringProperty(name);
        this.subname = new SimpleStringProperty(subname);
        this.distance = new SimpleStringProperty(distance);
        this.speed = new SimpleStringProperty(speed);
        this.time = new SimpleStringProperty(time);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSubname() {
        return subname.get();
    }

    public SimpleStringProperty subnameProperty() {
        return subname;
    }

    public String getDistance() {
        return distance.get();
    }

    public SimpleStringProperty distanceProperty() {
        return distance;
    }

    public String getSpeed() {
        return speed.get();
    }

    public SimpleStringProperty speedProperty() {
        return speed;
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSubname(String subname) {
        this.subname.set(subname);
    }

    public void setDistance(String distance) {
        this.distance.set(distance);
    }

    public void setSpeed(String speed) {
        this.speed.set(speed);
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    @Override
    public String toString() {
        return "ExpressWay{" +
                "name=" + name +
                ", subname=" + subname +
                ", distance=" + distance +
                ", speed=" + speed +
                ", time=" + time +
                '}';
    }
}