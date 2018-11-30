package net.ddns.starla.fnacsp.template.entities;

import org.junit.Test;

public class PressureTest {

    @Test(expected = EntityException.class)
    public void whenPressureBelowItsMin_ShouldThrowPressureException() {
        new Pressure(Pressure.MIN_PRESS_IN_ATM - .1);
    }

    @Test(expected = EntityException.class)
    public void whenPressureAboveItsMax_ShouldThrowIllegalArgumentException() {
        new Pressure(Pressure.MAX_PRESS_IN_ATM + .1);
    }
}