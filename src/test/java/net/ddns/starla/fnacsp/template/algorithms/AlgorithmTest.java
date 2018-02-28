package net.ddns.starla.fnacsp.template.algorithms;

import org.junit.Ignore;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static net.ddns.starla.fnacsp.template.algorithms.Algorithm.*;

public abstract class AlgorithmTest {

    final double hour;
    final int day;
    final int month;
    final int year;
    final String zoneId;
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

    AlgorithmTest(String zoneId, double hour, int day, int month, int year, double longitude, double latitude,
                  double pressure, double temperature, double expectedZenith, double expectedAzimuth,
                  double expectedRightAscension, double expectedDeclination, double expectedHourAngle) {

        this.zoneId = zoneId;
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

    final void compute() {
        algorithm.compute();
    }

    ZonedDateTime decimalToZonedDateTime() {
        int hour = (int) this.hour;
        int minute = (int) ((hour - (int) this.hour) * 60.0);
        int second = (int) ((((hour - (int) this.hour) * 60.0) - minute) * 60.0);
        int nanoOfSecond = (int) ((((((hour - (int) this.hour) * 60.0) - minute) * 60.0) - second) * 1e9);

        return ZonedDateTime.of(year, month, day, hour, minute, second, nanoOfSecond, ZoneId.of(zoneId));
    }

    @Test
    public void zenithInRange() {
        assertTrue(isInRange(0, PI, algorithm.getZenith()));
    }

    private boolean isInRange(double leftBound, double rightBound, double value) {
        return value >= leftBound && value <= rightBound;
    }

    @Test
    public void azimuthInRange() {
        assertTrue(isInRange(-PI, PI, algorithm.getAzimuth()));
    }

    @Test
    @Ignore
    public void rightAscensionInRange() {
        assertTrue(isInRange(0, PI2, algorithm.getRightAscension()));
    }

    @Test
    public void declinationInRange() {
        assertTrue(isInRange(-PIM, PIM, algorithm.getDeclination()));
    }

    @Test
    @Ignore
    public void hourAngleInRange() {
        assertTrue(isInRange(-PI, PI, algorithm.getHourAngle()));
    }

    @Test
    public void zenithAtUT() {
        assertEquals(expectedZenith, algorithm.getZenith(), delta);
    }

    @Test
    public void azimuthAtUT() {
        assertEquals(expectedAzimuth, algorithm.getAzimuth(), delta);
    }

    @Test
    public void rightAscensionAtUT() {
        assertEquals(expectedRightAscension, algorithm.getRightAscension(), delta);
    }

    @Test
    public void declinationAtUT() {
        assertEquals(expectedDeclination, algorithm.getDeclination(), delta);
    }

    @Test
    public void hourAngleAtUT() {
        assertEquals(expectedHourAngle, algorithm.getHourAngle(), delta);
    }

}