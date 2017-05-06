package net.ddns.starla.pattern.facade;

import net.ddns.starla.pattern.strategy.Algorithm;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SunPosition {

    private Algorithm algorithm;
    private ZonedDateTime zonedDateTime;
    private double hour;

    public void computePosition(Algorithm algorithm, ZonedDateTime zonedDateTime, double longitude, double latitude,
                                double pressure, double temperature) {

        this.algorithm = algorithm;
        this.zonedDateTime = zonedDateTime;

        timeZoneToUTC();
        timeToDecimal();

        this.algorithm.compute(hour, this.zonedDateTime.getDayOfMonth(), this.zonedDateTime.getMonthValue(),
                this.zonedDateTime.getYear(), longitude, latitude, pressure, temperature);
    }

    private void timeZoneToUTC() {
        zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    private void timeToDecimal() {
        hour = zonedDateTime.getHour() + zonedDateTime.getMinute() / 60.0 + zonedDateTime.getSecond() / 3600.0;
    }

    public double getZenith() {
        return algorithm.getZenith();
    }

    public double getAzimuth() {
        return algorithm.getAzimuth();
    }

    public double getRightAscension() {
        return algorithm.getRightAscension();
    }

    public double getDeclination() {
        return algorithm.getDeclination();
    }

    public double getHourAngle() {
        return algorithm.getHourAngle();
    }

    public boolean isItDay() {
        return getElevation() > 0.0;
    }

    public double getElevation() {
        return Algorithm.PIM - algorithm.getZenith();
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
