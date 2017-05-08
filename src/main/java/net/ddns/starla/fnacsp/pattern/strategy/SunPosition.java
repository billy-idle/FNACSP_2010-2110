package net.ddns.starla.fnacsp.pattern.strategy;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SunPosition {

    private static final ZoneId UTC = ZoneId.of("UTC");
    private final Algorithm algorithm;
    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    private final ZonedDateTime zonedDateTime;
    private ZonedDateTime zonedDateTimeAtUTC;
    private double hour;

    private SunPosition(Algorithm algorithm, ZonedDateTime zonedDateTime, double longitude, double latitude,
                        double pressure, double temperature) {

        this.algorithm = algorithm;
        this.zonedDateTime = zonedDateTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public static SunPosition Make(Algorithm algorithm, ZonedDateTime zonedDateTime, double longitude, double latitude,
                                   double pressure, double temperature) {

        assertInputParameters(zonedDateTime, longitude, latitude, pressure, temperature);
        return new SunPosition(algorithm, zonedDateTime, longitude, latitude, pressure, temperature);
    }

    private static void assertInputParameters(ZonedDateTime zonedDateTime, double longitude, double latitude, double pressure, double temperature) {
        ZonedDateTime leftBoundOfValidInterval =
                ZonedDateTime.of(2010, 1, 1, 0, 0, 0, 0, UTC);

        ZonedDateTime rightBoundOfValidInterval =
                ZonedDateTime.of(2110, 1, 1, 0, 0, 0, 0, UTC);

        if (zonedDateTime.withZoneSameInstant(UTC).isBefore(leftBoundOfValidInterval) ||
                zonedDateTime.withZoneSameInstant(UTC).isAfter(rightBoundOfValidInterval))
            throw new OutOfValidZonedDateTimeInterval();

        if (longitude < 0.0 || longitude > Algorithm.PI2) throw new LongitudeOutOfRange();
        if (latitude < -Algorithm.PIM || latitude > Algorithm.PIM) throw new LatitudeOutOfRange();

        //https://en.wikipedia.org/wiki/Atmospheric_pressure#Records
        if (pressure < 0.85862324204293 || pressure > 1.0696274364668) throw new PressureOutOfRange();

        //https://en.wikipedia.org/wiki/List_of_weather_records#Lowest_temperatures_ever_recorded
        //https://en.wikipedia.org/wiki/List_of_weather_records#Highest_temperatures_ever_recorded
        if (temperature < -89.2 || temperature > 54.0) throw new TemperatureOutOfRange();
    }

    public void computePosition() {
        timeZoneToUTC();
        timeToDecimal();

        this.algorithm.compute(hour, this.zonedDateTimeAtUTC.getDayOfMonth(), this.zonedDateTimeAtUTC.getMonthValue(),
                this.zonedDateTimeAtUTC.getYear(), longitude, latitude, pressure, temperature);
    }

    private void timeZoneToUTC() {
        zonedDateTimeAtUTC = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    private void timeToDecimal() {
        hour = zonedDateTimeAtUTC.getHour() + zonedDateTimeAtUTC.getMinute() / 60.0 + zonedDateTimeAtUTC.getSecond() / 3600.0;
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

    public String getZonedDateTime() {
        return zonedDateTime.toString();
    }

    static class OutOfValidZonedDateTimeInterval extends RuntimeException {
    }

    static class LongitudeOutOfRange extends RuntimeException {
    }

    static class LatitudeOutOfRange extends RuntimeException {
    }

    static class PressureOutOfRange extends RuntimeException {
    }

    static class TemperatureOutOfRange extends RuntimeException {
    }
}