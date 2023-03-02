package com.msproject.patterndesigns.adapter;

public class DiscBolivarAdapter implements DiscService {

    private DiscBolivarService discBolivarService;

    public DiscBolivarAdapter(DiscBolivarService discBolivarService) {
        this.discBolivarService = discBolivarService;
    }

    @Override
    public double getPrice() {
        return discBolivarService.getPriceInBolivars();
    }
}
