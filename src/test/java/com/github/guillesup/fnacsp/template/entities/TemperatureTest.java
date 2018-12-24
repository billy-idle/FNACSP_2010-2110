package com.github.guillesup.fnacsp.template.entities;

import org.junit.Test;

public class TemperatureTest {

    @Test(expected = EntityException.class)
    public void whenTemperatureBelowItsMin_ShouldThrowTemperatureException() {
        new Temperature(Temperature.MIN_TEMP_CELSIUS - .1);
    }

    @Test(expected = EntityException.class)
    public void whenTemperatureAboveItsMax_ShouldThrowTemperatureException() {
        new Temperature(Temperature.MAX_TEMP_CELSIUS + .1);
    }
}