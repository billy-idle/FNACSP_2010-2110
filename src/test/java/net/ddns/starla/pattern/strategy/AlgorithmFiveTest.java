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
public class AlgorithmFiveTest {

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

    public AlgorithmFiveTest(double hour, int day, int month, int year, double longitude, double latitude, double pressure,
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
                {0, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.7205, -2.75172, 5.3545, -0.334134, -9.25957},
                {1, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.60415, -2.27183, 5.35526, -0.333958, -8.99782},
                {2, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.43685, -1.95563, 5.35602, -0.333781, -8.73606},
                {3, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.24918, -1.72944, 5.35678, -0.333605, -8.4743},
                {4, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.0549, -1.54571, 5.35755, -0.333428, -8.21255},
                {5, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.86133, -1.37913, 5.35831, -0.333251, -7.95079},
                {6, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.67383, -1.21446, 5.35907, -0.333074, -7.68904},
                {7, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.49471, -1.0405, 5.35983, -0.332896, -7.42728},
                {8, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.3387, -0.847738, 5.36059, -0.332719, -7.16552},
                {9, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.20778, -0.628351, 5.36135, -0.332541, -6.90377},
                {10, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.11358, -0.378856, 5.36211, -0.332363, -6.64201},
                {11, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.06666, -0.104799, 5.36286, -0.332185, -6.38025},
                {12, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.07355, 0.177228, 5.36362, -0.332007, -6.1185},
                {13, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.13323, 0.446306, 5.36438, -0.331828, -5.85674},
                {14, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.23773, 0.688385, 5.36514, -0.33165, -5.59498},
                {15, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.3761, 0.900631, 5.3659, -0.331471, -5.33323},
                {16, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.53557, 1.08803, 5.36666, -0.331292, -5.07147},
                {17, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.72007, 1.259, 5.36742, -0.331113, -4.80971},
                {18, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.90948, 1.42343, 5.36818, -0.330933, -4.54795},
                {19, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.10359, 1.59325, 5.36894, -0.330754, -4.2862},
                {20, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.29672, 1.78559, 5.3697, -0.330574, -4.02444},
                {21, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.48042, 2.03, 5.37046, -0.330394, -3.76268},
                {22, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.63773, 2.38154, 5.37121, -0.330214, -3.50092},
                {23, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.73198, 2.90808, 5.37197, -0.330034, -3.23916}
        });
    }

    @Before
    public void computeSunPosition() {
        algorithm = new AlgorithmFactory().getInstance(Accuracy.HIGHEST);
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
