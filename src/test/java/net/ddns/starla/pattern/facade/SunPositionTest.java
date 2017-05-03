package net.ddns.starla.pattern.facade;

import net.ddns.starla.pattern.factory.Accuracy;
import net.ddns.starla.pattern.factory.AlgorithmFactory;
import net.ddns.starla.pattern.strategy.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SunPositionTest {

    private final double delta = 1.0E-05;
    private SunPosition sunPosition;

    @BeforeEach
    void setUp() {
        sunPosition = new SunPosition(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
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
    public void rightAscensionAZerotUT() throws Exception {
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

}