package com.msproject.patterndesigns.bridge;

public class Ferrari extends Vehicle {

    public Ferrari(Color color) {
        super(color);
    }

    @Override
    public String showMessage() {
        return "The Ferrari available is of the "+color.buildColorMessage();
    }
}
