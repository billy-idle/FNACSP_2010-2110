package net.ddns.starla.pattern.strategy;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AlgorithmThreeTest {

    private final double delta = 0.0001;
    private Algorithm algorithm;

    @Before
    public void setUp() throws Exception {
        algorithm = new Algorithm_3();
        computeSunPosition();
    }

    private void computeSunPosition() {
        algorithm.compute(0.0, 25, 1, 2020, 0.21787, 0.73117, 1.0, 20.0);
    }

    @Test
    public void zenithAtZeroUT() throws Exception {
        assertEquals(2.72049, algorithm.getZenith(), delta);
    }

    @Test
    public void azimuthAtZeroUT() throws Exception {
        assertEquals(-2.75167, algorithm.getAzimuth(), delta);
    }

    @Test
    public void rightAscensionAtZeroUT() throws Exception {
        assertEquals(5.35455, algorithm.getRightAscension(), delta);
    }

    @Test
    public void declinationAtZeroUT() throws Exception {
        assertEquals(-0.33413, algorithm.getDeclination(), delta);
    }

    @Test
    public void hourAngleAtZeroUT() throws Exception {
        assertEquals(-9.25955, algorithm.getHourAngle(), delta);
    }

    @Test
    public void zenithInRange() throws Exception {
        assertTrue(isInRange(0, Algorithm.PI, algorithm.getZenith()));
    }

    private boolean isInRange(double leftBound, double rightBound, double value) {
        return value >= leftBound && value <= rightBound;
    }

    @Test
    public void azimuthInRange() throws Exception {
        assertTrue(isInRange(-Algorithm.PI, Algorithm.PI, algorithm.getAzimuth()));
    }

    @Test
    public void rightAscensionInRange() throws Exception {
        assertTrue(isInRange(0, Algorithm.PI2, algorithm.getRightAscension()));
    }

    @Test
    public void declinationInRange() throws Exception {
        assertTrue(isInRange(-Algorithm.PIM, Algorithm.PIM, algorithm.getDeclination()));
    }

    @Test
    @Ignore
    public void hourAngleInRange() throws Exception {
        assertTrue(isInRange(-Algorithm.PI, Algorithm.PI, algorithm.getHourAngle()));
    }
}
