package net.ddns.starla.fnacsp.example;

import net.ddns.starla.fnacsp.template.SunPosition;
import org.jetbrains.annotations.Contract;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Computes the instant sun position using the current date-time from the system clock
 * in a specific time-zone (zoneId).
 */
public final class InstantSunPosition {

    private final String algorithmClassName;
    private final ZonedDateTime zonedDateTime;
    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    private SunPosition sunPosition;

    /**
     * @param algorithmClassName Valid names are any Algorithm subclass
     * @param zoneId             zoneId not null (time-zone)
     * @param longitude          [0, 2PI] rad
     * @param latitude           [-PI/2, PI/2] rad
     * @param pressure           [0.85862324204293, 1.0696274364668] atm
     * @param temperature        Between [-89.2, 54.0] °C
     * @see SunPosition#of(String, ZonedDateTime, double, double, double, double)
     */
    public InstantSunPosition(String algorithmClassName, String zoneId, double longitude, double latitude,
                              double pressure, double temperature) {

        this.algorithmClassName = algorithmClassName;
        this.zonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(zoneId));
        this.longitude = longitude;
        this.latitude = latitude;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public void compute() {
        sunPosition = SunPosition.of(algorithmClassName, zonedDateTime, longitude, latitude, pressure, temperature);
        sunPosition.compute();
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    @Contract(pure = true)
    public double getZenith() {
        return sunPosition.getZenith();
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    @Contract(pure = true)
    public double getAzimuth() {
        return sunPosition.getAzimuth();
    }

    /**
     * @return Right ascension [0,2PI] rad
     */
    @Contract(pure = true)
    public double getRightAscension() {
        return sunPosition.getRightAscension();
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    @Contract(pure = true)
    public double getDeclination() {
        return sunPosition.getDeclination();
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    @Contract(pure = true)
    public double getHourAngle() {
        return sunPosition.getHourAngle();
    }

    /**
     * @return True if the sun is above the horizon
     */
    @Contract(pure = true)
    public boolean isItDaylight() {
        return sunPosition.isItDaylight();
    }

    /**
     * @return Elevation angle [0,PIM]
     */
    @Contract(pure = true)
    public double getElevation() {
        return sunPosition.getElevation();
    }

    /**
     * @return ZonedDateTime used to compute the sun's position
     */
    @Contract(pure = true)
    public ZonedDateTime getZonedDateTime() {
        return sunPosition.getZonedDateTime();
    }
}
