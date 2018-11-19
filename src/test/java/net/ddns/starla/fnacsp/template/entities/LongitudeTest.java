package net.ddns.starla.fnacsp.template.entities;

import org.junit.Test;

public class LongitudeTest {

    @Test(expected = LongitudeException.class)
    public void whenLongitudeBelowItsMin_ShouldThrowLongitudeException() {
        new Longitude(Longitude.MIN_LONGITUDE_RAD - .1);
    }

    @Test(expected = LongitudeException.class)
    public void whenLongitudeAboveItsMax_ShouldThrowLongitudeException() {
        new Longitude(Longitude.MAX_LONGITUDE_RAD + .1);
    }
}