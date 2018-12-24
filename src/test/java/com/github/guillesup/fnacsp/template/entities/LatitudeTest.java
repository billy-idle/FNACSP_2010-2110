package com.github.guillesup.fnacsp.template.entities;

import org.junit.Test;

public class LatitudeTest {

    @Test(expected = EntityException.class)
    public void whenLatitudeBelowItsMin_ShouldThrowLatitudeException() {
        new Latitude(Latitude.MIN_LATITUDE_RAD - .1);
    }

    @Test(expected = EntityException.class)
    public void whenLatitudeAboveItsMax_ShouldThrowLatitudeException() {
        new Latitude(Latitude.MAX_LATITUDE_RAD + .1);
    }

}