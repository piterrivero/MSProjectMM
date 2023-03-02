package com.msproject.patterndesigns.bridge;

abstract class Vehicle {

    protected Color color;

    public Vehicle(Color color) {
        this.color = color;
    }

    abstract public String showMessage();

}
