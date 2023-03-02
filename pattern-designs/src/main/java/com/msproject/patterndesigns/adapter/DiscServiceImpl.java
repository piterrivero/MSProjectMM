package com.msproject.patterndesigns.adapter;

public class DiscServiceImpl implements DiscService {

    private Disc disc;

    public DiscServiceImpl(Disc disc) {
        this.disc = disc;
    }

    @Override
    public double getPrice() {
        return disc.getPrice();
    }
}
