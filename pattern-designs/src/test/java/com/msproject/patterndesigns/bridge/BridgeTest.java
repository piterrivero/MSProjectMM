package com.msproject.patterndesigns.bridge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BridgeTest {

    @Test
    public void shouldWorksWithBridge(){
        Vehicle ferrari = new Ferrari(new RedColor());
        Assertions.assertThat(ferrari.showMessage()).isEqualTo("The Ferrari available is of the color red");

        Vehicle ferrari2 = new Ferrari(new BlackColor());
        Assertions.assertThat(ferrari2.showMessage()).isEqualTo("The Ferrari available is of the color black");

        Vehicle bugatti = new Bugatti(new RedColor());
        Assertions.assertThat(bugatti.showMessage()).isEqualTo("The Bugatti available is of the color red");

        Vehicle bugatti2 = new Bugatti(new BlackColor());
        Assertions.assertThat(bugatti2.showMessage()).isEqualTo("The Bugatti available is of the color black");

        Vehicle toyota = new Toyota(new RedColor());
        Assertions.assertThat(toyota.showMessage()).isEqualTo("The Toyota available is of the color red");

        Vehicle toyota2 = new Toyota(new BlackColor());
        Assertions.assertThat(toyota2.showMessage()).isEqualTo("The Toyota available is of the color black");

        Vehicle hummer = new Hummer(new RedColor());
        Assertions.assertThat(hummer.showMessage()).isEqualTo("The Hummer available is of the color red");

        Vehicle hummer2 = new Hummer(new BlackColor());
        Assertions.assertThat(hummer2.showMessage()).isEqualTo("The Hummer available is of the color black");
    }

}
