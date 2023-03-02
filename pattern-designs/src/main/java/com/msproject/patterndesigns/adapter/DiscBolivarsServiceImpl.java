package com.msproject.patterndesigns.adapter;

public class DiscBolivarsServiceImpl implements DiscBolivarService {

    private double factor = 4;

    private Disc disc;

    public DiscBolivarsServiceImpl(Disc disc) {
        this.disc = disc;
    }

    public double getPriceInBolivars() {
        return convertCurrency(disc.getPrice());
    }

    private double convertCurrency(double price) {
        return price * factor;
    }

}
