package net.ddns.starla.se;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class SunEphemeris {
    private final Ephemeris ephemeris;

    public SunEphemeris(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        String algorithm = "AlgorithmFive";
        ZoneId zoneId1 = ZoneId.of(zoneId);
        ChronoUnit timeUnit = ChronoUnit.MINUTES;
        ZonedDateTime start = ZonedDateTime.now(zoneId1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        ZonedDateTime end = start.plusDays(1);

        this.ephemeris = new Ephemeris(algorithm, timeUnit, start, end, longitude, latitude, pressure, temperature);
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append(String.format("Sun Ephemeris for %1$s on %2$tB %2$te of %2$tY%n",
                this.getTimeInterval()[0].getZone(),
                this.getTimeInterval()[0])).append("\n").append(String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "ToD")).append("\n");

        for (int i = 0; i < this.getTimeInterval().length; i++) {
            output.append(String.format("%tT", this.getTimeInterval()[i]));
            output.append(String.format("%10.3f", this.getZenith()[i]));
            output.append(String.format("%10.3f", this.getAzimuth()[i]));
            output.append(String.format("%15.3f", this.getRightAscension()[i]));
            output.append(String.format("%15.3f", this.getDeclination()[i]));
            output.append(String.format("%15.3f", this.getHourAngle()[i]));
            output.append(String.format("%10s%n", this.getTimeOfDay()[i]));
        }

        output.append("\n" + "* angles in radians (rad)");

        return output.toString();
    }
}
