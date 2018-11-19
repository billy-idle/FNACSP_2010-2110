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
        StringBuilder table = new StringBuilder();
        StringBuilder row = new StringBuilder();
        StringBuilder output = new StringBuilder();

        String desc = String.format("Sun Ephemeris for %1$s at Long. %3$f and Lat. %4$f on %2$tB %2$te of %2$tY%n%n",
                se.getTimeInterval()[0].getZone(), se.getTimeInterval()[0], se.getLongitude(), se.getLatitude());

        String tableHeader = String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s %8s %8s%n", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "Pres.", "Temp.", "ToD");

        for (int i = 0; i < se.getTimeInterval().length; i++) {
            row.append(String.format("%tT", se.getTimeInterval()[i]));
            row.append(String.format("%9.3f", se.getZenith()[i]));
            row.append(String.format("%12.3f", se.getAzimuth()[i]));
            row.append(String.format("%12.3f", se.getRightAscension()[i]));
            row.append(String.format("%15.3f", se.getDeclination()[i]));
            row.append(String.format("%15.3f", se.getHourAngle()[i]));
            row.append(String.format("%10.1f", se.getPressure()[i]));
            row.append(String.format("%10.1f", se.getTemperature()[i]));
            row.append(String.format("%10s%n", se.getTimeOfDay()[i]));
        }

        table.append(tableHeader).append(row);

        String footer = String.format("%n%s%n%s%n%s%n%s%n", "* angles in radians (rad)",
                "* Pressure in atmospheres (atm)", "* Temperature in Celsius degrees (°C)",
                "* ToD stands for \"Time of Day\"");

        output.append(desc).append(table).append(footer);

        return output.toString();
    }
}