package net.ddns.starla.fnacsp.algorithms.strategy;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.lang.Math.*;
import static java.time.temporal.ChronoUnit.NANOS;

public abstract class Algorithm {

    public static final double PIM = 1.57079632679490;
    public static final double PI2 = 6.28318530717959;
    static final double PI = 3.14159265358979;

    private static final ZonedDateTime MIDPOINT_OF_THE_INTERVAL = ZonedDateTime.of(2060, 1, 1,
            0, 0, 0, 0, ZoneId.of("UTC"));

    private static final ZoneId UTC = ZoneId.of("UTC");
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
     * @param zonedDateTime Wrapper of time's related values.
     * @param longitude     [0, 2PI] rad
     * @param latitude      [-PI/2,PI/2] rad
     * @param pressure      [0.85862324204293, 1.0696274364668] atm
     * @param temperature   Between [-89.2, 54.0] °C
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Lowest_temperatures_ever_recorded">Lowest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Highest_temperatures_ever_recorded">Highest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/Atmospheric_pressure#Records">Atmospheric pressure records</a>
     */
    public abstract void compute(ZonedDateTime zonedDateTime, double longitude,
                                 double latitude, double pressure, double temperature);

    final void timeScaleComputation(ZonedDateTime zonedDateTime) {
        zonedDateTimeToUTC(zonedDateTime);
        double dt = 96.4 + 0.567 * (zonedDateTimeAtUTC.getYear() - 2061);
        t = daysFromTheMidpointOfTheInterval();
        te = t + 1.1574e-5 * dt;
    }

    private void zonedDateTimeToUTC(ZonedDateTime zonedDateTime) {
        zonedDateTimeAtUTC = zonedDateTime.withZoneSameInstant(UTC);
    }

    private double daysFromTheMidpointOfTheInterval() {
        return NANOS.between(MIDPOINT_OF_THE_INTERVAL, zonedDateTimeAtUTC) / 8.64e13;
    }

    final void shiftHourAngleToItsConventionalRange() {
        hourAngle = ((hourAngle + PI) % PI2) - PI;
    }

    final void applyFinalComputationallyOptimizedProcedure(double latitude, double pressure, double temperature) {
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
            applyRefractionCorrection(pressure, temperature);

        zenith = PIM - ep - de;
    }

    private void applyRefractionCorrection(double pressure, double temperature) {
        de = (0.08422 * pressure) / ((273.0 + temperature) * tan(ep + 0.003138 / (ep + 0.08919)));
    }

    final void shiftRightAscensionToItsConventionalRange() {
        rightAscension %= PI2;
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    public final double getZenith() {
        return zenith;
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

}