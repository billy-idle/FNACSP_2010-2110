package net.ddns.starla.fnacsp.template.algorithms;

import net.ddns.starla.fnacsp.template.entities.Entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Template Design Pattern
 */
public abstract class Algorithm {

    static final double PI = 3.14159265358979;
    static final double PIM = 1.57079632679490;
    static final double PI2 = 6.28318530717959;
    final double longitude;
    private final ZonedDateTime zonedDateTime;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    double rightAscension;
    double declination;
    double hourAngle;
    double t;
    double te;
    private ZonedDateTime zonedDateTimeAtUTC;
    private double zenith;
    private double azimuth;
    private double ep;
    private double de;

    /**
     * @param time        Instance of Time entity class
     *                    (From 2010 to 2110 at UTC)
     * @param longitude   Instance of Longitude entity class.
     *                    (longitude between [0, 2PI] rad)
     * @param latitude    Instance of Latitude entity class.
     *                    (latitude between [-PI/2,PI/2] rad)
     * @param pressure    Instance of Pressure entity class.
     *                    (Between [0.85862324204293, 1.0696274364668] atm)
     * @param temperature Instance of Temperature entity class.
     */
    Algorithm(Entity time, Entity longitude, Entity latitude, Entity pressure, Entity temperature) {
        this.zonedDateTime = (ZonedDateTime) time.getValue();
        this.longitude = (double) longitude.getValue();
        this.latitude = (double) latitude.getValue();
        this.pressure = (double) pressure.getValue();
        this.temperature = (double) temperature.getValue();
    }

    /**
     * Computes the sun position
     */
    public final void compute() {
        timeScaleComputation();
        accuracyLevel();
        shiftHourAngleToItsConventionalRange();
        applyFinalComputationallyOptimizedProcedure();
    }

    private void timeScaleComputation() {
        zonedDateTimeToUTC();
        double dt = 96.4 + 0.567 * (zonedDateTimeAtUTC.getYear() - 2061);
        t = daysFromTheMidpointOfTheInterval();
        te = t + 1.1574e-5 * dt;
    }

    /**
     * Template Method
     */
    protected abstract void accuracyLevel();

    private void shiftHourAngleToItsConventionalRange() {
        hourAngle = ((hourAngle + Algorithm.PI) % Algorithm.PI2) - Algorithm.PI;
    }

    private void applyFinalComputationallyOptimizedProcedure() {
        double sp = Math.sin(latitude);
        double cp = Math.sqrt((1 - sp * sp));
        double sd = Math.sin(declination);
        double cd = Math.sqrt(1 - sd * sd);
        double sH = Math.sin(hourAngle);
        double cH = Math.cos(hourAngle);
        double se0 = sp * sd + cp * cd * cH;

        ep = Math.asin(se0) - 4.26e-5 * Math.sqrt(1.0 - se0 * se0);

        azimuth = Math.atan2(sH, cH * sp - sd * cp / cd);

        if (ep > 0.0)
            applyRefractionCorrection();

        zenith = Algorithm.PIM - ep - de;
    }

    private void zonedDateTimeToUTC() {
        zonedDateTimeAtUTC = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
    }

    private double daysFromTheMidpointOfTheInterval() {
        return ChronoUnit.NANOS.between(ZonedDateTime.of(2060, 1,
                1, 0, 0, 0, 0, ZoneOffset.UTC), zonedDateTimeAtUTC) / 8.64e13;
    }

    private void applyRefractionCorrection() {
        de = (0.08422 * pressure) / ((273.0 + temperature) * Math.tan(ep + 0.003138 / (ep + 0.08919)));
    }

    final void shiftRightAscensionToItsConventionalRange() {
        rightAscension %= Algorithm.PI2;
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    public final double getAzimuth() {
        return azimuth;
    }

    /**
     * @return Right ascension [0,2PI] rad
     */
    public final double getRightAscension() {
        return rightAscension;
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    public final double getDeclination() {
        return declination;
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    public final double getHourAngle() {
        return hourAngle;
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
        return Algorithm.PIM - getZenith();
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    public final double getZenith() {
        return zenith;
    }

    /**
     * @return zonedDateTime
     */
    public final ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    /**
     * @return longitude
     */
    public final double getLongitude() {
        return longitude;
    }

    /**
     * @return latitude
     */
    public final double getLatitude() {
        return latitude;
    }

    /**
     * @return pressure
     */
    public final double getPressure() {
        return pressure;
    }

    /**
     * @return temperature
     */
    public final double getTemperature() {
        return temperature;
    }
}