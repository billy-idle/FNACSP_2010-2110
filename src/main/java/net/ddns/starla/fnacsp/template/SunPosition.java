package net.ddns.starla.fnacsp.template;

import net.ddns.starla.fnacsp.factory.AlgorithmFactory;
import net.ddns.starla.fnacsp.template.algorithms.Algorithm;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
     * @param zonedDateTime      From 2010 to 2110 at UTC
     * @param longitude          [0, 2PI] rad
     * @param latitude           [-PI/2,PI/2] rad
     * @param pressure           [0.85862324204293, 1.0696274364668] atm
     * @param temperature        Between [-89.2, 54.0] °C
     * @return A SunPosition instance
     * @see Algorithm#compute(ZonedDateTime, double, double, double, double)
     */
    @NotNull
    public static SunPosition of(String algorithmClassName, ZonedDateTime zonedDateTime, double longitude, double latitude,
                                 double pressure, double temperature) {

        InputAssessment.assess(algorithmClassName, zonedDateTime, longitude, latitude, pressure, temperature);

        return new SunPosition(AlgorithmFactory.createInstance(algorithmClassName), zonedDateTime, longitude, latitude,
                pressure, temperature);
    }

    public void compute() {
        algorithm.compute(zonedDateTime, longitude, latitude, pressure, temperature);
    }

    /**
     * @return Return the same zonedDateTime passed.
     * @see SunPosition#of(String, ZonedDateTime, double, double, double, double)
     */
    @Contract(pure = true)
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
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
}
