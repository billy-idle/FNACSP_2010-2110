package com.github.guillesup.se;

import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SunEphemerisTest {

    private final String zoneId = "Europe/Rome";
    private final ZonedDateTime start = ZonedDateTime.of(2020, 1, 25, 1, 0, 0, 0, ZoneId.of(zoneId));
    private final ZonedDateTime end = start.plusDays(1);
    private final double delta = 1.0E-4;
    private final double[] expectedZenith = {
            2.7205, 2.60415, 2.43685, 2.24918, 2.0549, 1.86133, 1.67383, 1.49471, 1.3387, 1.20778, 1.11358, 1.06666,
            1.07355, 1.13323, 1.23773, 1.3761, 1.53557, 1.72007, 1.90948, 2.10359, 2.29672, 2.48042, 2.63773, 2.73198
    };
    private final double[] expectedAzimuth = {
            -2.75172, -2.27183, -1.95563, -1.72944, -1.54571, -1.37913, -1.21446, -1.0405, -0.847738, -0.628351,
            -0.378856, -0.104799, 0.177228, 0.446306, 0.688385, 0.900631, 1.08803, 1.259, 1.42343, 1.59325, 1.78559,
            2.03, 2.38154, 2.90808
    };
    private final double[] expectedRightAscension = {
            5.3545, 5.35526, 5.35602, 5.35678, 5.35755, 5.35831, 5.35907, 5.35983, 5.36059, 5.36135, 5.36211, 5.36286,
            5.36362, 5.36438, 5.36514, 5.3659, 5.36666, 5.36742, 5.36818, 5.36894, 5.3697, 5.37046, 5.37121, 5.37197

    };
    private final double[] expectedDeclination = {
            -0.334134, -0.333958, -0.333781, -0.333605, -0.333428, -0.333251, -0.333074, -0.332896, -0.332719, -0.332541,
            -0.332363, -0.332185, -0.332007, -0.331828, -0.33165, -0.331471, -0.331292, -0.331113, -0.330933, -0.330754,
            -0.330574, -0.330394, -0.330214, -0.330034
    };
    private final double[] expectedHourAngle = {
            -9.25957, -8.99782, -8.73606, -8.4743, -8.21255, -7.95079, -7.68904, -7.42728, -7.16552, -6.90377, -6.64201,
            -6.38025, -6.1185, -5.85674, -5.59498, -5.33323, -5.07147, -4.80971, -4.54795, -4.2862, -4.02444, -3.76268,
            -3.50092, -3.23916
    };

    private SunEphemeris sunEphemeris;

    @Before
    public void computeEphemeris() {
        double temperature = 20.0;
        double pressure = 1.0;
        double latitude = 0.73117;
        double longitude = 0.21787;
        ChronoUnit timeUnit = ChronoUnit.HOURS;
        String algorithm = "AlgorithmFive";
        sunEphemeris = new SunEphemeris(algorithm, timeUnit, start, end, longitude, latitude, pressure, temperature);
        sunEphemeris.compute();
    }

    @Test
    public void compareZenith() {
        assertArrayEquals(expectedZenith, sunEphemeris.getZenith(), delta);
    }

    @Test
    public void compareAzimuth() {
        assertArrayEquals(expectedAzimuth, sunEphemeris.getAzimuth(), delta);
    }

    @Test
    public void compareRightAscension() {
        assertArrayEquals(expectedRightAscension, sunEphemeris.getRightAscension(), delta);
    }

    @Test
    public void compareDeclination() {
        assertArrayEquals(expectedDeclination, sunEphemeris.getDeclination(), delta);
    }

    @Test
    public void compareHourAngle() {
        assertArrayEquals(expectedHourAngle, sunEphemeris.getHourAngle(), delta);
    }

    @Test
    public void printingOutput() {
        double temperature = 20.0;
        double pressure = 1.0;
        double latitude = 0.73117;
        double longitude = 0.21787;
        String zoneId = "Europe/Rome";
        sunEphemeris = new SunEphemeris(zoneId, longitude, latitude, pressure, temperature);
        sunEphemeris.compute();
        String expectedOutput = output();
        assertEquals(expectedOutput, sunEphemeris.toString());
    }

    private String output() {
        StringBuilder table = new StringBuilder();
        StringBuilder row = new StringBuilder();
        StringBuilder output = new StringBuilder();

        String desc = String.format("Sun SunEphemeris for %1$s at Long. %3$f and Lat. %4$f on %2$tB %2$te of %2$tY%n%n",
                sunEphemeris.getTimeInterval()[0].getZone(), sunEphemeris.getTimeInterval()[0], sunEphemeris.getLongitude(), sunEphemeris.getLatitude());

        String tableHeader = String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s %8s %8s%n", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "Pres.", "Temp.", "ToD");

        for (int i = 0; i < sunEphemeris.getTimeInterval().length; i++) {
            row.append(String.format("%tT", sunEphemeris.getTimeInterval()[i]));
            row.append(String.format("%9.3f", sunEphemeris.getZenith()[i]));
            row.append(String.format("%12.3f", sunEphemeris.getAzimuth()[i]));
            row.append(String.format("%12.3f", sunEphemeris.getRightAscension()[i]));
            row.append(String.format("%15.3f", sunEphemeris.getDeclination()[i]));
            row.append(String.format("%15.3f", sunEphemeris.getHourAngle()[i]));
            row.append(String.format("%10.1f", sunEphemeris.getPressure()[i]));
            row.append(String.format("%10.1f", sunEphemeris.getTemperature()[i]));
            row.append(String.format("%10s%n", sunEphemeris.getTimeOfDay()[i]));
        }

        table.append(tableHeader).append(row);

        String footer = String.format("%n%s%n%s%n%s%n%s%n", "* angles in radians (rad)",
                "* Pressure in atmospheres (atm)", "* Temperature in Celsius degrees (°C)",
                "* ToD stands for \"Time of Day\"");

        output.append(desc).append(table).append(footer);

        return output.toString();
    }
}