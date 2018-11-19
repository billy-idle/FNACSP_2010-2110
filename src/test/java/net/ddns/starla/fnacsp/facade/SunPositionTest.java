package net.ddns.starla.fnacsp.facade;

import org.junit.Test;

import java.time.zone.ZoneRulesException;

import static org.junit.Assert.assertEquals;

public class SunPositionTest {

    private final static String zoneId = "Europe/Rome";
    private SunPosition sp;

    @Test(expected = ZoneRulesException.class)
    public void whenZoneIdUnknown_ShouldThrowZoneRulesException() {
        new SunPosition("Europe/Bogota", 0.21787, 0.73117, 1.0, 20.0).compute();
    }

    @Test
    public void printingOutput() {
        double longitude = 0.21787;
        double temperature = 20.0;
        double pressure = 1.0;
        double latitude = 0.73117;
        sp = new SunPosition(zoneId, longitude, latitude, pressure, temperature);
        sp.compute();
        assertEquals(getExpectedString(), sp.toString());
    }

    private String getExpectedString() {
        StringBuilder table = new StringBuilder();
        StringBuilder row = new StringBuilder();
        StringBuilder output = new StringBuilder();

        String desc = String.format("Sun Ephemeris for %1$s at Long. %3$f and Lat. %4$f on %2$tB %2$te of %2$tY%n%n",
                sp.getZonedDateTime().getZone(), sp.getZonedDateTime(), sp.getLongitude(), sp.getLatitude());

        String tableHeader = String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s %8s %8s%n", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "Pres.", "Temp.", "ToD");

        row.append(String.format("%tT", sp.getZonedDateTime()));
        row.append(String.format("%9.3f", sp.getZenith()));
        row.append(String.format("%12.3f", sp.getAzimuth()));
        row.append(String.format("%12.3f", sp.getRightAscension()));
        row.append(String.format("%15.3f", sp.getDeclination()));
        row.append(String.format("%15.3f", sp.getHourAngle()));
        row.append(String.format("%10.1f", sp.getPressure()));
        row.append(String.format("%10.1f", sp.getTemperature()));
        row.append(String.format("%10s%n", sp.getTimeOfDay()));

        table.append(tableHeader).append(row);

        String footer = String.format("%n%s%n%s%n%s%n%s%n", "* angles in radians (rad)",
                "* Pressure in atmospheres (atm)", "* Temperature in Celsius degrees (°C)",
                "* ToD stands for \"Time of Day\"");

        output.append(desc).append(table).append(footer);

        return output.toString();
    }
}