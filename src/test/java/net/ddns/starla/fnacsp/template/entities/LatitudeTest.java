package net.ddns.starla.fnacsp.template.entities;

import org.junit.Test;

public class LatitudeTest {

    @Test(expected = LatitudeException.class)
    public void whenLatitudeBelowItsMin_ShouldThrowLatitudeException() {
        new Latitude(Latitude.MIN_LATITUDE_RAD - .1);
    }

    @Test(expected = LatitudeException.class)
    public void whenLatitudeAboveItsMax_ShouldThrowLatitudeException() {
        new Latitude(Latitude.MAX_LATITUDE_RAD + .1);
    }

}