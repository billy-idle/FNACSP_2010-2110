package net.ddns.starla.fnacsp.pattern.strategy.top;

import static java.lang.Math.*;

public abstract class Algorithm implements Cloneable {

    public static final double PI = 3.14159265358979;
    public static final double PIM = 1.57079632679490;
    public static final double PI2 = 6.28318530717959;
    protected double rightAscension;
    protected double declination;
    protected double hourAngle;
    protected double t;
    protected double te;
    private double zenith;
    private double azimuth;
    private double ep;
    private double de;

    /**
     * @param hour        At UT [0-24]
     * @param day         [1-31]
     * @param month       [1-12]
     * @param year        [2010-2110]
     * @param longitude   [0, 2PI] rad
     * @param latitude    [-PI/2,PI/2] rad
     * @param pressure    [0.85862324204293, 1.0696274364668] atm
     * @param temperature Between [-89.2, 54.0] °C
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Lowest_temperatures_ever_recorded">Lowest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Highest_temperatures_ever_recorded">Highest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/Atmospheric_pressure#Records">Atmospheric pressure records</a>
     */
    public abstract void compute(double hour, int day, int month, int year, double longitude,
                                 double latitude, double pressure, double temperature);


    protected final void timeScaleComputation(double hour, int day, int month, int year) {
        double dt = 96.4 + 0.567 * (year - 2061);

        if (month <= 2) {
            month += 12;
            year--;
        }

        t = (int) (365.25 * (year - 2000)) + (int) (30.6001 * (month + 1)) - (int) (0.01 * year) + day + 0.0416667
                * hour - 21958.0;

        te = t + 1.1574e-5 * dt;
    }

    protected final void shiftHourAngleToItsConventionalRange() {
        hourAngle = ((hourAngle + PI) % PI2) - PI;
    }

    protected final void applyFinalComputationallyOptimizedProcedure(double latitude, double pressure, double temperature) {
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

    protected final void shiftRightAscensionToItsConventionalRange() {
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

    /**
     * @return A shallow copy of this object.
     */
    final public Algorithm clone() {
        try {
            return (Algorithm) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}