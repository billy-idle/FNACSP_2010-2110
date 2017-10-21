package net.ddns.starla.fnacsp.algorithms.strategy.top;

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

    /**
     * Prevents other classes from instantiating the class.
     * This is useful when a class wants to control all calls to create new instances of itself.
     */
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
     * @see Algorithm#compute(ZonedDateTime, double, double, double, double)
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
            throw new IllegalArgumentException("ZoneDateTime must be between JAN-01-2010[UTC] and JAN-01-2110[UTC]");
    }

    private static void assertLongitude(double longitude) {
        if (longitude < 0.0 || longitude > Algorithm.PI2)
            throw new IllegalArgumentException("Longitude must be between [0, 2PI] rad");
    }

    private static void assertLatitude(double latitude) {
        if (latitude < -Algorithm.PIM || latitude > Algorithm.PIM)
            throw new IllegalArgumentException("Latitude must be between [-PI/2, PI/2] rad");
    }

    private static void assertPressure(double pressure) {
        if (pressure < 0.85862324204293 || pressure > 1.0696274364668)
            throw new IllegalArgumentException("Pressure must be between [0.85862324204293, 1.0696274364668] atm");
    }

    private static void assertTemperature(double temperature) {
        if (temperature < -89.2 || temperature > 54.0)
            throw new IllegalArgumentException("Temperature must be between [-89.2, 54.0] °C");
    }

    public void computePosition() {
        this.algorithm.compute(zonedDateTime, longitude, latitude, pressure, temperature);
    }

    /**
     * @return Return a zonedDateTime object
     */
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
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
    public boolean isItDaylight() {
        return getElevation() > 0.0;
    }

    private double getElevation() {
        return Algorithm.PIM - algorithm.getZenith();
    }
}