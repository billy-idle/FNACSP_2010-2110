package net.ddns.starla.fnacsp.pattern.strategy;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class SunPosition {

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

    /**
     * @param algorithm     It must be get it through AlgorithmFactory
     * @param zonedDateTime Between JAN-01-2010[UTC] and JAN-01-2110[UTC]
     * @param longitude     [0, 2PI] rad
     * @param latitude      [-PI/2,PI/2] rad
     * @param pressure      [0.85862324204293, 1.0696274364668] atm
     * @param temperature   Between [-89.2, 54.0] °C
     * @return A SunPosition instance
     * @see Algorithm#compute(double, int, int, int, double, double, double, double)
     */
    public static SunPosition of(Algorithm algorithm, ZonedDateTime zonedDateTime, double longitude, double latitude,
                                 double pressure, double temperature) {

        assertInputParameters(zonedDateTime, longitude, latitude, pressure, temperature);
        return new SunPosition(algorithm, zonedDateTime, longitude, latitude, pressure, temperature);
    }

    private static void assertInputParameters(ZonedDateTime zonedDateTime, double longitude, double latitude, double pressure,
                                              double temperature) {
        assertZonedDateTime(zonedDateTime);
        assertLongitude(longitude);
        assertLatitude(latitude);
        assertPressure(pressure);
        assertTemperature(temperature);
    }

    private static void assertZonedDateTime(ZonedDateTime zonedDateTime) {
        ZonedDateTime leftBoundOfValidInterval = ZonedDateTime.of(2010, 1, 1, 0, 0, 0, 0, UTC);
        ZonedDateTime rightBoundOfValidInterval = ZonedDateTime.of(2110, 1, 1, 0, 0, 0, 0, UTC);

        if (zonedDateTime.withZoneSameInstant(UTC).isBefore(leftBoundOfValidInterval) ||
                zonedDateTime.withZoneSameInstant(UTC).isAfter(rightBoundOfValidInterval))
            throw new ZonedDateTimeOutOfRange();
    }

    private static void assertLongitude(double longitude) {
        if (longitude < 0.0 || longitude > Algorithm.PI2) throw new LongitudeOutOfRange();
    }

    private static void assertLatitude(double latitude) {
        if (latitude < -Algorithm.PIM || latitude > Algorithm.PIM) throw new LatitudeOutOfRange();
    }

    private static void assertPressure(double pressure) {
        if (pressure < 0.85862324204293 || pressure > 1.0696274364668) throw new PressureOutOfRange();
    }

    private static void assertTemperature(double temperature) {
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

    /**
     * @return Zenith angle [0,PI] rad
     */
    public double getZenith() {
        return algorithm.getZenith();
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    public double getAzimuth() {
        return algorithm.getAzimuth();
    }

    /**
     * @return Right ascension [0,2PI] rad
     */
    public double getRightAscension() {
        return algorithm.getRightAscension();
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    public double getDeclination() {
        return algorithm.getDeclination();
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    public double getHourAngle() {
        return algorithm.getHourAngle();
    }

    /**
     * @return True if the sun is above the horizon
     */
    public boolean isItDay() {
        return getElevation() > 0.0;
    }

    private double getElevation() {
        return Algorithm.PIM - algorithm.getZenith();
    }

    /**
     * @return String representation of zonedDateTime
     */
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    static class ZonedDateTimeOutOfRange extends RuntimeException {
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