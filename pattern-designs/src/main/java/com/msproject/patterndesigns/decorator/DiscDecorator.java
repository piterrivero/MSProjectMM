package com.msproject.patterndesigns.decorator;

public abstract class DiscDecorator implements IDisc {

    protected IDisc discDecorated;

    public DiscDecorator(IDisc discDecorated) {
        this.discDecorated = discDecorated;
    }

    @Override
    public String getDiscDescription(Disc disc) {
        return discDecorated.getDiscDescription(disc);
    }
}
