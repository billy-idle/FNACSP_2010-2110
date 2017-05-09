package net.ddns.starla.fnacsp.pattern.strategy;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class SunPositionTest {

    private static SunPosition sunPosition;
    private final double delta = 1.0E-05;

    @BeforeClass
    public static void setUp() {
        sunPosition = SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, 0.73117, 1.0, 20.0);

        sunPosition.computePosition();
    }

    @Test
    public void zenithAtZeroUT() throws Exception {
        assertEquals(2.7205, sunPosition.getZenith(), delta);
    }

    @Test
    public void azimuthAtZeroUT() throws Exception {
        assertEquals(-2.75172, sunPosition.getAzimuth(), delta);
    }

    @Test
    public void rightAscensionAtZeroUT() throws Exception {
        assertEquals(5.3545, sunPosition.getRightAscension(), delta);
    }

    @Test
    public void declinationAtZeroUT() throws Exception {
        assertEquals(-0.334134, sunPosition.getDeclination(), delta);
    }

    @Test
    public void hourAngleAtZeroUT() throws Exception {
        assertEquals(-9.25957, sunPosition.getHourAngle(), delta);
    }

    @Test
    public void isItNight() throws Exception {
        assertTrue(!isElevationAnglePositive());
    }

    private boolean isElevationAnglePositive() {
        return (Algorithm.PIM - sunPosition.getZenith()) > 0.0;
    }

    @Test(expected = SunPosition.ZonedDateTimeOutOfRange.class)
    public void whenZonedDateTimeBefore2010_ShouldThrowZonedDateTimeOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2010, 1, 1, 0, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, 0.73117, 1.0, 20.0);
    }

    @Test(expected = SunPosition.ZonedDateTimeOutOfRange.class)
    public void whenZonedDateTimeAfter2110_ShouldThrowZonedDateTimeOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2110, 1, 1, 2, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, 0.73117, 1.0, 20.0);
    }

    @Test(expected = SunPosition.LongitudeOutOfRange.class)
    public void whenLongitudeNegative_ShouldThrowLongitudeOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), -1, 0.73117, 1.0, 20.0);
    }

    @Test(expected = SunPosition.LongitudeOutOfRange.class)
    public void whenLongitudeGreaterThanPI2_ShouldThrowLongitudeOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), Algorithm.PI2 + .1, 0.73117, 1.0, 20.0);
    }

    @Test(expected = SunPosition.LatitudeOutOfRange.class)
    public void whenLatitudeLessThanMinusPIM_ShouldThrowLatitudeOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, -Algorithm.PIM - .1, 1.0, 20.0);
    }

    @Test(expected = SunPosition.LatitudeOutOfRange.class)
    public void whenLatitudeGreaterThanMinusPIM_ShouldThrowLatitudeOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, Algorithm.PIM + .1, 1.0, 20.0);
    }

    @Test(expected = SunPosition.PressureOutOfRange.class)
    public void whenPressureBelowMinRecord_ShouldThrowPressureOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, 0.73117, 0.85862324204293 - .1, 20.0);
    }

    @Test(expected = SunPosition.PressureOutOfRange.class)
    public void whenPressureAboveMaxRecord_ShouldThrowPressureOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, 0.73117, 1.0696274364668 + .1, 20.0);
    }

    @Test(expected = SunPosition.TemperatureOutOfRange.class)
    public void whenTemperatureBelowMinRecord_ShouldThrowTemperatureOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, 0.73117, 1.0, -89.2 - .1);
    }

    @Test(expected = SunPosition.TemperatureOutOfRange.class)
    public void whenTemperatureAboveMaxRecord_ShouldThrowTemperatureOutOfRange() throws Exception {
        SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0,
                        ZoneId.of("Europe/Rome")), 0.21787, 0.73117, 1.0, 54.0 + .1);
    }
}
