package net.ddns.starla.fnacsp.pattern.facade;

import net.ddns.starla.fnacsp.pattern.strategy.Accuracy;
import net.ddns.starla.fnacsp.pattern.strategy.Algorithm;
import net.ddns.starla.fnacsp.pattern.strategy.AlgorithmFactory;
import net.ddns.starla.fnacsp.pattern.strategy.SunPosition;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Computes the instant sun position with the time-zone ID passed as String, with the highest precision algorithm (Algorithm #5)
 */
public class SunPositionNow {
    private final SunPosition sunPosition;

    /**
     * @param zoneId      time-zone ID
     * @param longitude   [0, 2PI] rad
     * @param latitude    [-PI/2, PI/2] rad
     * @param pressure    [0.85862324204293, 1.0696274364668] atm
     * @param temperature Between [-89.2, 54.0] °C
     * @see SunPosition#Make(Algorithm, ZonedDateTime, double, double, double, double)
     */
    public SunPositionNow(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        sunPosition = SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.now(ZoneId.of(zoneId)), longitude, latitude, pressure, temperature);

        sunPosition.computePosition();
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    public double getZenith() {
        return sunPosition.getZenith();
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    public double getAzimuth() {
        return sunPosition.getAzimuth();
    }


    /**
     * @return Right ascension [0,2PI] rad
     */
    public double getRightAscension() {
        return sunPosition.getRightAscension();
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    public double getDeclination() {
        return sunPosition.getDeclination();
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    public double getHourAngle() {
        return sunPosition.getHourAngle();
    }

    /**
     * @return True if the sun is above the horizon
     */
    public boolean isItDay() {
        return sunPosition.isItDay();
    }

    /**
     * @return String representation of zonedDateTime
     */
    public String getZonedDateTime() {
        return sunPosition.getZonedDateTime();
    }
}
