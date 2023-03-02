package com.msproject.patterndesigns.decorator;

public class ImportedDisc implements IDisc {

    private String country;

    public ImportedDisc(String country) {
        this.country = country;
    }
    @Override
    public String getDiscDescription(Disc disc) {
        return "The disc "+disc.getTitle()+" is IMPORTED from "+country+" and its price is of "+ disc.getPrice();
    }

}
