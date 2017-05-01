import static java.lang.Math.*;

public abstract class Algorithm {

    static final double PI = 3.14159265358979;
    static final double PI2 = 6.28318530717959;
    static final double PIM = 1.57079632679490;
    double rightAscension;
    double declination;
    double hourAngle;
    double t;
    double te;
    private double zenith;
    private double azimuth;
    private double ep;
    private double de;

    public abstract void computeSunPosition(double hour, int day, int month, int year, double longitude,
                                            double latitude, double pressure, double temperature);


    void timeScaleComputation(double hour, int day, int month, int year) {
        double dt = 96.4 + 0.567 * (double) (year - 2061);

        if (month <= 2) {
            month = month + 12;
            year = year - 1;
        }

        t = (double) ((int) (365.25 * (double) (year - 2000)) + (int) (30.6001 * (double) (month + 1)) -
                (int) (0.01 * (double) (year)) + day) + 0.0416667 * hour - 21958.0;

        te = t + 1.1574e-5 * dt;
    }

    void shiftHourAngleToItsConventionalRange(double latitude, double pressure, double temperature) {
        hourAngle = ((hourAngle + PI) % PI2) - PI;
    }

    void applyFinalComputationallyOptimizedProcedure(double latitude, double pressure, double temperature) {
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

    void shiftRightAscensionToItsConventionalRange() {
        rightAscension = rightAscension % PI2;
    }

    public double getZenith() {
        return zenith;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public double getRightAscension() {
        return rightAscension;
    }

    public double getDeclination() {
        return declination;
    }

    public double getHourAngle() {
        return hourAngle;
    }
}