package com.msproject.patterndesigns.decorator;

public class NationalDisc implements IDisc {

    private String country;

    public NationalDisc(String country) {
        this.country = country;
    }

    @Override
    public String getDiscDescription(Disc disc) {
        return "The disc "+disc.getTitle()+" is from "+country+" and its price is of "+ disc.getPrice();
    }

}
