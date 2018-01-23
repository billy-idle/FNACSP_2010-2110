package net.ddns.starla.fnacsp.algorithms.strategy;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class SunPositionTest {

    private final static String algorithmClassName = "AlgorithmFive";
    private final static String zoneId = "Europe/Rome";
    private static SunPosition sunPosition;
    private final double delta = 1.0E-05;

    @BeforeClass
    public static void setUp() {
        sunPosition = SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0,
                0, 0, zoneId, 0.21787, 0.73117, 1.0, 20.0);

        sunPosition.computePosition();
    }

    @Test
    public void zenithAtZeroUT() {
        assertEquals(2.7205, sunPosition.getZenith(), delta);
    }

    @Test
    public void azimuthAtZeroUT() {
        assertEquals(-2.75172, sunPosition.getAzimuth(), delta);
    }

    @Test
    public void rightAscensionAtZeroUT() {
        assertEquals(5.3545, sunPosition.getRightAscension(), delta);
    }

    @Test
    public void declinationAtZeroUT() {
        assertEquals(-0.334134, sunPosition.getDeclination(), delta);
    }

    @Test
    public void hourAngleAtZeroUT() {
        assertEquals(-9.25957, sunPosition.getHourAngle(), delta);
    }

    @Test
    public void isItNight() {
        assertTrue(!isElevationAnglePositive());
    }

    private boolean isElevationAnglePositive() {
        return (Algorithm.PIM - sunPosition.getZenith()) > 0.0;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeBefore2010_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2010, 1, 1, 0, 0, 0,
                0, zoneId, 0.21787, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeAfter2110_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2110, 1, 1, 2, 0, 0,
                0, zoneId, 0.21787, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeNegative_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, -1, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeGreaterThanPI2_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, Algorithm.PI2 + .1, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeLessThanMinusPIM_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, 0.21787, -Algorithm.PIM - .1, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeGreaterThanMinusPIM_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, 0.21787, Algorithm.PIM + .1, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureBelowMinRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, 0.21787, 0.73117, 0.85862324204293 - .1,
                20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureAboveMaxRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, 0.21787, 0.73117, 1.0696274364668 + .1,
                20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureBelowMinRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, 0.21787, 0.73117, 1.0, -89.2 - .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureAboveMaxRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, 2020, 1, 25, 1, 0, 0,
                0, zoneId, 0.21787, 0.73117, 1.0, 54.0 + .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAlgorithmClassNameNotFound_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName.concat("2498#$@%^&("), 2020, 1, 25, 1,
                0, 0, 0, zoneId, 0.21787, 0.73117, 1.0,
                54.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenClassNameFoundButNotAlgorithmType_ShouldThrowIllegalArgumentException() {
        SunPosition.of("DummyClass", 2020, 1, 25, 1, 0,
                0, 0, zoneId, 0.21787, 0.73117, 1.0, 54.0);
    }
}
