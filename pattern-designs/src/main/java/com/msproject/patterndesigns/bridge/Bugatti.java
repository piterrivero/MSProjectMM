package com.msproject.patterndesigns.bridge;

public class Bugatti extends Vehicle {

    public Bugatti(Color color) {
        super(color);
    }

    @Override
    public String showMessage() {
        return "The Bugatti available is of the "+color.buildColorMessage();
    }
}
