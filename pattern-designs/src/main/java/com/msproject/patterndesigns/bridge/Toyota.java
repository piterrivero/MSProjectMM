package com.msproject.patterndesigns.bridge;

public class Toyota extends Vehicle {

    public Toyota(Color color) {
        super(color);
    }

    @Override
    public String showMessage() {
        return "The Toyota available is of the "+color.buildColorMessage();
    }
}
