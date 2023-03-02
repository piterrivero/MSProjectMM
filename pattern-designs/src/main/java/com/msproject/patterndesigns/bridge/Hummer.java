package com.msproject.patterndesigns.bridge;

public class Hummer extends Vehicle {

    public Hummer(Color color) {
        super(color);
    }

    @Override
    public String showMessage() {
        return "The Hummer available is of the "+color.buildColorMessage();
    }
}
