package net.ddns.starla.se;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class SunEphemeris {
    private final Ephemeris ephemeris;

    public SunEphemeris(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        ZonedDateTime start = ZonedDateTime.now(ZoneId.of(zoneId)).withHour(0).withMinute(0).withSecond(0).withNano(0);

        this.ephemeris = new Ephemeris("AlgorithmFive", ChronoUnit.MINUTES, start, start.plusDays(1), longitude,
                latitude, pressure, temperature);
    }

    public void compute() {
        ephemeris.compute();
    }

    public double[] getZenith() {
        return ephemeris.getZenith();
    }

    public double[] getAzimuth() {
        return ephemeris.getAzimuth();
    }

    public double[] getRightAscension() {
        return ephemeris.getRightAscension();
    }

    public double[] getDeclination() {
        return ephemeris.getDeclination();
    }

    public double[] getHourAngle() {
        return ephemeris.getHourAngle();
    }

    public ZonedDateTime[] getTimeInterval() {
        return ephemeris.getTimeInterval();
    }

    public String[] getTimeOfDay() {
        return ephemeris.getTimeOfDay();
    }

    public double getLongitude() {
        return ephemeris.getLongitude();
    }

    public double getLatitude() {
        return ephemeris.getLatitude();
    }

    public double[] getPressure() {
        return ephemeris.getPressure();
    }

    public double[] getTemperature() {
        return ephemeris.getTemperature();
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        StringBuilder row = new StringBuilder();
        StringBuilder output = new StringBuilder();

        String desc = String.format("Sun Ephemeris for %1$s at Long. %3$f and Lat. %4$f on %2$tB %2$te of %2$tY%n%n",
                ephemeris.getTimeInterval()[0].getZone(), ephemeris.getTimeInterval()[0], ephemeris.getLongitude(),
                ephemeris.getLatitude());

        String tableHeader = String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s %8s %8s%n", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "Pres.", "Temp.", "ToD");

        for (int i = 0; i < ephemeris.getTimeInterval().length; i++) {
            row.append(String.format("%tT", ephemeris.getTimeInterval()[i]));
            row.append(String.format("%9.3f", ephemeris.getZenith()[i]));
            row.append(String.format("%12.3f", ephemeris.getAzimuth()[i]));
            row.append(String.format("%12.3f", ephemeris.getRightAscension()[i]));
            row.append(String.format("%15.3f", ephemeris.getDeclination()[i]));
            row.append(String.format("%15.3f", ephemeris.getHourAngle()[i]));
            row.append(String.format("%10.1f", ephemeris.getPressure()[i]));
            row.append(String.format("%10.1f", ephemeris.getTemperature()[i]));
            row.append(String.format("%10s%n", ephemeris.getTimeOfDay()[i]));
        }

        table.append(tableHeader).append(row);

        String footer = String.format("%n%s%n%s%n%s%n%s%n", "* angles in radians (rad)",
                "* Pressure in atmospheres (atm)", "* Temperature in Celsius degrees (°C)",
                "* ToD stands for \"Time of Day\"");

        output.append(desc).append(table).append(footer);

        return output.toString();
    }

}
