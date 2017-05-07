package net.ddns.starla.fnacsp.pattern.facade;

import net.ddns.starla.fnacsp.pattern.strategy.Algorithm;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SunPosition {

    private final Algorithm algorithm;
    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    private ZonedDateTime zonedDateTime;
    private double hour;

    public SunPosition(Algorithm algorithm, ZonedDateTime zonedDateTime, double longitude, double latitude,
                       double pressure, double temperature) {

        this.algorithm = algorithm;
        this.zonedDateTime = zonedDateTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public void computePosition() {
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

    private double getElevation() {
        return Algorithm.PIM - algorithm.getZenith();
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
