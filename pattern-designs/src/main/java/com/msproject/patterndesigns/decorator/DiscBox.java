package com.msproject.patterndesigns.decorator;

public class DiscBox extends DiscDecorator {

    public DiscBox(IDisc discDecorated) {
        super(discDecorated);
    }

    @Override
    public String getDiscDescription(Disc disc) {
        return discDecorated.getDiscDescription(disc) + " - additionally it is an special edition with a box so its final price is of "+ disc.getPrice() * 2;
    }


}
