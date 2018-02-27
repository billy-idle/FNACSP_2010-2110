package net.ddns.starla.fnacsp.template;

import org.junit.Test;

import java.time.ZonedDateTime;

import static net.ddns.starla.fnacsp.template.InputAssessment.*;


public class SunPositionTest {

    private static final ZonedDateTime MIDPOINT_OF_VALID_TIME_INTERVAL = LEFT_BOUND_OF_VALID_TIME_INTERVAL.plusYears(50);
    private final static String algorithmClassName = "AlgorithmFive";
    private final double longitude = 0.21787;
    private final double latitude = 0.73117;
    private final double pressure = 1.0;
    private final double temperature = 20.0;

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeBefore2010_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, LEFT_BOUND_OF_VALID_TIME_INTERVAL.minusNanos(1), longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeAfter2110_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, RIGHT_BOUND_OF_VALID_TIME_INTERVAL.plusNanos(1), longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeBelowItsMin_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, MIN_LONGITUDE - .1, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeBeyondItsMax_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, MAX_LONGITUDE + .1, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeBelowItsMin_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, MIN_LATITUDE - .1,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeBeyondItsMax_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, MAX_LATITUDE + .1,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureBelowItsMin_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude,
                MIN_PRESSURE - .1, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureAboveItsMax_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude,
                MAX_PRESSURE + .1, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureBelowItsMin_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude, pressure,
                MIN_TEMPERATURE - .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureAboveItsMax_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude, pressure,
                MAX_TEMPERATURE + .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAlgorithmClassNameNotFound_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName.concat("2498#$@%^&("), MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenClassNameFoundButNotAlgorithmType_ShouldThrowIllegalArgumentException() {
        SunPosition.of("DummyClass", MIDPOINT_OF_VALID_TIME_INTERVAL, longitude, latitude, pressure,
                temperature);
    }
}