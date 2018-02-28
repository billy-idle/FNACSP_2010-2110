package net.ddns.starla.fnacsp.facade;

import net.ddns.starla.fnacsp.factory.AlgorithmFactory;
import net.ddns.starla.fnacsp.template.algorithms.Algorithm;
import org.jetbrains.annotations.Contract;

import java.time.ZonedDateTime;

/**
 * Facade Design Pattern
 */
public final class SunPosition {

    private final Algorithm algorithm;

    /**
     * @param algorithmClassName Valid names are any Algorithm subclass
     * @param zonedDateTime      From 2010 to 2110 at UTC
     * @param longitude          [0, 2PI] rad
     * @param latitude           [-PI/2,PI/2] rad
     * @param pressure           [0.85862324204293, 1.0696274364668] atm
     * @param temperature        Between [-89.2, 54.0] °C
     * @see Algorithm#compute()
     */
    public SunPosition(String algorithmClassName, ZonedDateTime zonedDateTime, double longitude,
                       double latitude, double pressure, double temperature) {
        this.algorithm = AlgorithmFactory.createInstance(algorithmClassName, zonedDateTime, longitude, latitude,
                pressure, temperature);
    }

    /**
     * Computes the sun position
     */
    public void compute() {
        algorithm.compute();
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    @Contract(pure = true)
    public double getZenith() {
        return algorithm.getZenith();
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    @Contract(pure = true)
    public double getAzimuth() {
        return algorithm.getAzimuth();
    }

    /**
     * @return Right ascension [0,2PI] rad
     */
    @Contract(pure = true)
    public double getRightAscension() {
        return algorithm.getRightAscension();
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    @Contract(pure = true)
    public double getDeclination() {
        return algorithm.getDeclination();
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    @Contract(pure = true)
    public double getHourAngle() {
        return algorithm.getHourAngle();
    }

    /**
     * @return True if the sun is above the horizon
     */
    @Contract(pure = true)
    public boolean isItDaylight() {
        return algorithm.isItDaylight();
    }

    /**
     * @return Elevation angle [0,PIM]
     */
    @Contract(pure = true)
    public double getElevation() {
        return algorithm.getElevation();
    }

    /**
     * @return zonedDateTime
     */
    @Contract(pure = true)
    public ZonedDateTime getZonedDateTime() {
        return algorithm.getZonedDateTime();
    }

    /**
     * @return longitude
     */
    @Contract(pure = true)
    public double getLongitude() {
        return algorithm.getLongitude();
    }

    /**
     * @return latitude
     */
    @Contract(pure = true)
    public double getLatitude() {
        return algorithm.getLatitude();
    }

    /**
     * @return pressure
     */
    @Contract(pure = true)
    public double getPressure() {
        return algorithm.getPressure();
    }

    /**
     * @return temperature
     */
    @Contract(pure = true)
    public double getTemperature() {
        return algorithm.getTemperature();
    }
}
