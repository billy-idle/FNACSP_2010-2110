package net.ddns.starla.se;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SunEphemerisTest {

    private SunEphemeris se;

    @Test
    public void printingOutput() {
        double temperature = 20.0;
        double pressure = 1.0;
        double latitude = 0.73117;
        double longitude = 0.21787;
        String zoneId = "Europe/Rome";
        se = new SunEphemeris(zoneId, longitude, latitude, pressure, temperature);
        se.compute();
        String expectedOutput = output();
        assertEquals(expectedOutput, se.toString());
    }

    private String output() {
        StringBuilder output = new StringBuilder();

        output.append(String.format("Sun Ephemeris for %1$s on %2$tB %2$te of %2$tY%n",
                se.getTimeInterval()[0].getZone(),
                se.getTimeInterval()[0])).append("\n").append(String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "ToD")).append("\n");

        for (int i = 0; i < se.getTimeInterval().length; i++) {
            output.append(String.format("%tT", se.getTimeInterval()[i]));
            output.append(String.format("%10.3f", se.getZenith()[i]));
            output.append(String.format("%10.3f", se.getAzimuth()[i]));
            output.append(String.format("%15.3f", se.getRightAscension()[i]));
            output.append(String.format("%15.3f", se.getDeclination()[i]));
            output.append(String.format("%15.3f", se.getHourAngle()[i]));
            output.append(String.format("%10s%n", se.getTimeOfDay()[i]));
        }

        output.append("\n" + "* angles in radians (rad)");

        return output.toString();
    }
}