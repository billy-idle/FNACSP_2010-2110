package net.ddns.starla.fnacsp.template;

import net.ddns.starla.fnacsp.factory.AlgorithmFactory;
import net.ddns.starla.fnacsp.template.algorithms.Algorithm;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Facade Design Pattern
 */
public final class SunPosition {

    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    private final Algorithm algorithm;
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
     * @param algorithmClassName Valid names are any Algorithm subclass
     * @param year               Between 2010 - 2110
     * @param month              Between 1 - 12
     * @param dayOfMonth         Between 1 - 31
     * @param hour               Between 0 - 23
     * @param minute             Between 0 - 59
     * @param second             Between 0 - 59
     * @param nanoOfSecond       Between 0 - 999,999,999
     * @param zoneId             zoneId (time-zone) not null
     * @param longitude          [0, 2PI] rad
     * @param latitude           [-PI/2,PI/2] rad
     * @param pressure           [0.85862324204293, 1.0696274364668] atm
     * @param temperature        Between [-89.2, 54.0] °C
     * @return A SunPosition instance
     * @see Algorithm#compute(ZonedDateTime, double, double, double, double)
     */
    public static SunPosition of(String algorithmClassName, int year, int month, int dayOfMonth, int hour, int minute,
                                 int second, int nanoOfSecond, String zoneId, double longitude, double latitude,
                                 double pressure, double temperature) {

        InputAssessment.assess(algorithmClassName, year, month, dayOfMonth, hour, minute, second, nanoOfSecond,
                zoneId, longitude, latitude, pressure, temperature);

        return new SunPosition(AlgorithmFactory.createInstance(algorithmClassName),
                ZonedDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond, ZoneId.of(zoneId)),
                longitude, latitude, pressure, temperature);
    }

    public void compute() {
        algorithm.compute(zonedDateTime, longitude, latitude, pressure, temperature);
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

    /**
     * @return Elevation angle [0,PIM]
     */
    public double getElevation() {
        return Algorithm.PIM - algorithm.getZenith();
    }
}
