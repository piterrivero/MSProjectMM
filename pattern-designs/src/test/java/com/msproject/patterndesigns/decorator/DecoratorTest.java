package com.msproject.patterndesigns.decorator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DecoratorTest {

    private Disc disc;

    @BeforeEach
    public void init(){
        disc = new Disc();
        disc.setId(1);
        disc.setTitle("Fear of the Dark");
        disc.setPrice(20);
        disc.setYear("1985");
    }

    @Test
    public void shouldWorksWithNational(){
        IDisc national = new NationalDisc("Italy");
        Assertions.assertThat(national.getDiscDescription(disc)).isEqualTo("The disc Fear of the Dark is from Italy, and its price is of 20");
    }

    @Test
    public void shouldWorksWithImported(){
        IDisc imported = new ImportedDisc("USA");
        Assertions.assertThat(imported.getDiscDescription(disc)).isEqualTo("The disc Fear of the Dark is IMPORTED from USA, and its price is of 20");
    }

    @Test
    public void shouldWorksWithNationalBoxDecorator(){
        IDisc national = new NationalDisc("Italy");

        IDisc box = new DiscBox(national);
        Assertions.assertThat(box.getDiscDescription(disc)).isEqualTo("The disc Fear of the Dark is from Italy and its price is of 20 - additionally it is an special edition with a box so its final price is of 40");
    }

    @Test
    public void shouldWorksWithImportedBoxDecorator(){
        IDisc imported = new ImportedDisc("USA");

        IDisc box2 = new DiscBox(imported);
        Assertions.assertThat(box2.getDiscDescription(disc)).isEqualTo("The disc Fear of the Dark is IMPORTED from USA and its price is of 20 - additionally it is an special edition with a box so its final price is of 40");
    }

}
