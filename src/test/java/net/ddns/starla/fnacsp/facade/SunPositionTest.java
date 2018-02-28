package net.ddns.starla.fnacsp.facade;

import net.ddns.starla.fnacsp.template.entities.AtmPressure;
import net.ddns.starla.fnacsp.template.entities.Temperature;
import org.junit.Test;

import static net.ddns.starla.fnacsp.template.entities.Coordinates.*;
import static net.ddns.starla.fnacsp.template.entities.Time.*;


public class SunPositionTest {

    private final static String algorithmClassName = "AlgorithmFive";
    private final double longitude = 0.21787;
    private final double latitude = 0.73117;
    private final double pressure = 1.0;
    private final double temperature = 20.0;

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeBefore2010_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, BEGINNING_OF_VALID_TIME_INTERVAL.minusNanos(1), longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeAfter2110_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, END_OF_VALID_TIME_INTERVAL.plusNanos(1), longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, MIN_LONGITUDE_VALUE - .1, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeBeyondItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, MAX_LONGITUDE_VALUE + .1, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, MIN_LATITUDE_VALUE - .1,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeBeyondItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, MAX_LATITUDE_VALUE + .1,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude,
                AtmPressure.MIN_VALUE - .1, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureAboveItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude,
                AtmPressure.MAX_VALUE + .1, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude, pressure,
                Temperature.MIN_VALUE - .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureAboveItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude, pressure,
                Temperature.MAX_VALUE + .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAlgorithmClassNameNotFound_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName.concat("2498#$@%^&("), MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenClassNameFoundButNotAlgorithmType_ShouldThrowIllegalArgumentException() {
        new SunPosition("DummyClass", MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude, pressure,
                temperature);
    }
}