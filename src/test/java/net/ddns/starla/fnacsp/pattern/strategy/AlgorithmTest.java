package net.ddns.starla.fnacsp.pattern.strategy;

import net.ddns.starla.fnacsp.algorithms.strategy.top.Algorithm;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static net.ddns.starla.fnacsp.algorithms.strategy.top.Algorithm.*;

public abstract class AlgorithmTest {

    final double hour;
    final int day;
    final int month;
    final int year;
    final double longitude;
    final double latitude;
    final double pressure;
    final double temperature;
    private final double delta = 1.0E-04;
    private final double expectedZenith;
    private final double expectedAzimuth;
    private final double expectedRightAscension;
    private final double expectedDeclination;
    private final double expectedHourAngle;
    Algorithm algorithm;

    AlgorithmTest(double hour, int day, int month, int year, double longitude, double latitude, double pressure,
                  double temperature, double expectedZenith, double expectedAzimuth, double expectedRightAscension,
                  double expectedDeclination, double expectedHourAngle) {

        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pressure = pressure;
        this.temperature = temperature;
        this.expectedZenith = expectedZenith;
        this.expectedAzimuth = expectedAzimuth;
        this.expectedRightAscension = expectedRightAscension;
        this.expectedDeclination = expectedDeclination;
        this.expectedHourAngle = expectedHourAngle;
    }

    @Test
    public void zenithInRange() throws Exception {
        assertTrue(isInRange(0, PI, algorithm.getZenith()));
    }

    private boolean isInRange(double leftBound, double rightBound, double value) {
        return value >= leftBound && value <= rightBound;
    }

    @Test
    public void azimuthInRange() throws Exception {
        assertTrue(isInRange(-PI, PI, algorithm.getAzimuth()));
    }

    @Test
    @Ignore
    public void rightAscensionInRange() throws Exception {
        assertTrue(isInRange(0, PI2, algorithm.getRightAscension()));
    }

    @Test
    public void declinationInRange() throws Exception {
        assertTrue(isInRange(-PIM, PIM, algorithm.getDeclination()));
    }

    @Test
    @Ignore
    public void hourAngleInRange() throws Exception {
        assertTrue(isInRange(-PI, PI, algorithm.getHourAngle()));
    }

    @Test
    public void zenithAtUT() throws Exception {
        assertEquals(expectedZenith, algorithm.getZenith(), delta);
    }

    @Test
    public void azimuthAtUT() throws Exception {
        assertEquals(expectedAzimuth, algorithm.getAzimuth(), delta);
    }

    @Test
    public void rightAscensionAtUT() throws Exception {
        assertEquals(expectedRightAscension, algorithm.getRightAscension(), delta);
    }

    @Test
    public void declinationAtUT() throws Exception {
        assertEquals(expectedDeclination, algorithm.getDeclination(), delta);
    }

    @Test
    public void hourAngleAtUT() throws Exception {
        assertEquals(expectedHourAngle, algorithm.getHourAngle(), delta);
    }

    @Test
    public void clonedObjectsAreNotTheSame() throws Exception {
        assertNotSame(algorithm, algorithm.newInstance());
    }
}