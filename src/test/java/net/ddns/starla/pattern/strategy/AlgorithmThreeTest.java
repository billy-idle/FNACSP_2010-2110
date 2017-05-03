package net.ddns.starla.pattern.strategy;

import net.ddns.starla.pattern.factory.Accuracy;
import net.ddns.starla.pattern.factory.AlgorithmFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class AlgorithmThreeTest {

    private final double delta = 1.0E-05;
    private Algorithm algorithm;
    private double hour;
    private int day;
    private int month;
    private int year;
    private double longitude;
    private double latitude;
    private double pressure;
    private double temperature;
    private double expectedZenith;
    private double expectedAzimuth;
    private double expectedRightAscension;
    private double expectedDeclination;
    private double expectedHourAngle;

    public AlgorithmThreeTest(double hour, int day, int month, int year, double longitude, double latitude, double pressure,
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

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.72049, -2.75167, 5.35455, -0.33413, -9.25955},
                {1, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.60413, -2.27179, 5.35531, -0.333954, -8.99779},
                {2, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.43683, -1.95561, 5.35607, -0.333778, -8.73603},
                {3, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.24916, -1.72942, 5.35683, -0.333601, -8.47428},
                {4, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.05488, -1.5457, 5.35759, -0.333424, -8.21252},
                {5, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.86131, -1.37912, 5.35835, -0.333247, -7.95076},
                {6, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.67381, -1.21444, 5.35911, -0.33307, -7.68901},
                {7, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.49469, -1.04048, 5.35987, -0.332893, -7.42725},
                {8, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.33868, -0.847718, 5.36063, -0.332715, -7.16549},
                {9, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.20777, -0.628327, 5.36139, -0.332538, -6.90374},
                {10, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.11357, -0.378828, 5.36215, -0.33236, -6.64198},
                {11, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.06666, -0.104767, 5.36291, -0.332182, -6.38022},
                {12, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.07355, 0.17726, 5.36367, -0.332004, -6.11847},
                {13, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.13323, 0.446337, 5.36443, -0.331825, -5.85671},
                {14, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.23774, 0.688413, 5.36519, -0.331647, -5.59495},
                {15, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.37612, 0.900656, 5.36595, -0.331468, -5.33319},
                {16, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.53559, 1.08806, 5.36671, -0.331289, -5.07144},
                {17, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.72009, 1.25902, 5.36746, -0.33111, -4.80968},
                {18, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.9095, 1.42345, 5.36822, -0.330931, -4.54792},
                {19, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.10361, 1.59327, 5.36898, -0.330751, -4.28616},
                {20, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.29675, 1.78561, 5.36974, -0.330572, -4.02441},
                {21, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.48044, 2.03004, 5.3705, -0.330392, -3.76265},
                {22, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.63775, 2.3816, 5.37126, -0.330212, -3.50089},
                {23, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.73198, 2.90816, 5.37201, -0.330032, -3.23913}
        });
    }

    @Before
    public void computeSunPosition() {
        algorithm = new AlgorithmFactory().getInstance(Accuracy.MID);
        algorithm.compute(hour, day, month, year, longitude, latitude, pressure, temperature);
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

    public void rightAscensionInRange() throws Exception {
        assertTrue(isInRange(0, Algorithm.PI2, algorithm.getRightAscension()));
    }

    @Test
    public void declinationInRange() throws Exception {
        assertTrue(isInRange(-Algorithm.PIM, Algorithm.PIM, algorithm.getDeclination()));
    }

    public void hourAngleInRange() throws Exception {
        assertTrue(isInRange(-Algorithm.PI, Algorithm.PI, algorithm.getHourAngle()));
    }
}
