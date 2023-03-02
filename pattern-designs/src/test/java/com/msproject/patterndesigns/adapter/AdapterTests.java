package com.msproject.patterndesigns.adapter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdapterTests {

    private DiscService discService;

    private Disc disc;


    @BeforeEach
    public void init(){
        disc = new Disc(1,"The number of the beast",10);
    }

    @Test
    public void shouldWorksWithoutAdapter(){

        discService = new DiscServiceImpl(disc);

        Assertions.assertThat(discService.getPrice()).isEqualTo(10);
    }

    @Test
    public void shouldWorksOnBolivarsWithoutAdapter(){

        DiscBolivarsServiceImpl discBolivars = new DiscBolivarsServiceImpl(disc);

        Assertions.assertThat(discBolivars.getPriceInBolivars()).isEqualTo(40);
    }
    @Test
    public void shouldWorksOnBothWithoutSameInterfaceAndAdapter(){

        discService = new DiscServiceImpl(disc);

        Assertions.assertThat(discService.getPrice()).isEqualTo(10);

        discService = new DiscBolivarAdapter(new DiscBolivarsServiceImpl(disc));

        Assertions.assertThat(discService.getPrice()).isEqualTo(40);

    }


}
