package net.ddns.starla.fnacsp.template.algorithms;

import net.ddns.starla.fnacsp.template.entities.AtmPressure;
import net.ddns.starla.fnacsp.template.entities.Coordinates;
import net.ddns.starla.fnacsp.template.entities.Temperature;
import net.ddns.starla.fnacsp.template.entities.Time;
import org.jetbrains.annotations.Contract;

import java.time.ZonedDateTime;

import static java.lang.Math.*;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.NANOS;
import static net.ddns.starla.fnacsp.template.entities.Time.MIDPOINT_OF_VALID_TIME_INTERVAL;

/**
 * Template Design Pattern
 */
public abstract class Algorithm {

    static final double PI = 3.14159265358979;
    static final double PIM = 1.57079632679490;
    static final double PI2 = 6.28318530717959;
    protected double longitude;
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
    private ZonedDateTime zonedDateTime;
    private double latitude;
    private double pressure;
    private double temperature;

    /**
     * @param time        Instance of Time entity class
     *                    (From 2010 to 2110 at UTC)
     * @param coordinates Instance of Coordinates entity class.
     *                    (longitude between [0, 2PI] rad)
     *                    (latitude between [-PI/2,PI/2] rad)
     * @param atmPressure Instance of AtmPressure entity class.
     *                    (Between [0.85862324204293, 1.0696274364668] atm)
     * @param temperature Instance of Temperature entity class.
     *                    (Between [-89.2, 54.0] °C)
     */
    public Algorithm(Time time, Coordinates coordinates, AtmPressure atmPressure, Temperature temperature) {
        this.zonedDateTime = time.getZonedDateTime();
        this.latitude = coordinates.getLatitude();
        this.longitude = coordinates.getLongitude();
        this.pressure = atmPressure.getPressure();
        this.temperature = temperature.getDegrees();
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
        hourAngle = ((hourAngle + PI) % PI2) - PI;
    }

    private void applyFinalComputationallyOptimizedProcedure() {
        double sp = sin(latitude);
        double cp = sqrt((1 - sp * sp));
        double sd = sin(declination);
        double cd = sqrt(1 - sd * sd);
        double sH = sin(hourAngle);
        double cH = cos(hourAngle);
        double se0 = sp * sd + cp * cd * cH;

        ep = asin(se0) - 4.26e-5 * sqrt(1.0 - se0 * se0);

        azimuth = atan2(sH, cH * sp - sd * cp / cd);

        if (ep > 0.0)
            applyRefractionCorrection();

        zenith = PIM - ep - de;
    }

    private void zonedDateTimeToUTC() {
        zonedDateTimeAtUTC = zonedDateTime.withZoneSameInstant(UTC);
    }

    private double daysFromTheMidpointOfTheInterval() {
        return NANOS.between(MIDPOINT_OF_VALID_TIME_INTERVAL, zonedDateTimeAtUTC) / 8.64e13;
    }

    private void applyRefractionCorrection() {
        de = (0.08422 * pressure) / ((273.0 + temperature) * tan(ep + 0.003138 / (ep + 0.08919)));
    }

    final void shiftRightAscensionToItsConventionalRange() {
        rightAscension %= PI2;
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    @Contract(pure = true)
    public final double getAzimuth() {
        return azimuth;
    }

    /**
     * @return Right ascension [0,2PI] rad
     */
    @Contract(pure = true)
    public final double getRightAscension() {
        return rightAscension;
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    @Contract(pure = true)
    public final double getDeclination() {
        return declination;
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    @Contract(pure = true)
    public final double getHourAngle() {
        return hourAngle;
    }

    /**
     * @return True if the sun is above the horizon
     */
    @Contract(pure = true)
    public boolean isItDaylight() {
        return getElevation() > 0.0;
    }

    /**
     * @return Elevation angle [0,PIM]
     */
    @Contract(pure = true)
    public double getElevation() {
        return Algorithm.PIM - getZenith();
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    @Contract(pure = true)
    public final double getZenith() {
        return zenith;
    }

    /**
     * @return zonedDateTime
     */
    @Contract(pure = true)
    public final ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    /**
     * @return longitude
     */
    @Contract(pure = true)
    public final double getLongitude() {
        return longitude;
    }

    /**
     * @return latitude
     */
    @Contract(pure = true)
    public final double getLatitude() {
        return latitude;
    }

    /**
     * @return pressure
     */
    @Contract(pure = true)
    public final double getPressure() {
        return pressure;
    }

    /**
     * @return temperature
     */
    @Contract(pure = true)
    public final double getTemperature() {
        return temperature;
    }
}