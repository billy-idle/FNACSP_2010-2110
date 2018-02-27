package net.ddns.starla.fnacsp.template;

import net.ddns.starla.fnacsp.factory.AlgorithmFactory;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

public class InputAssessment {

    static final double MIN_PRESSURE = 0.85862324204293;
    static final double MAX_PRESSURE = 1.0696274364668;
    static final double MIN_TEMPERATURE = -89.2;
    static final double MAX_TEMPERATURE = 54.0;
    static final double MIN_LONGITUDE = 0.0;
    static final double MAX_LONGITUDE = 6.28318530717959;
    static final double MIN_LATITUDE = -1.57079632679490;
    static final double MAX_LATITUDE = 1.57079632679490;
    static final ZonedDateTime LEFT_BOUND_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2010, 1,
            1, 0, 0, 0, 0, UTC);
    static final ZonedDateTime RIGHT_BOUND_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2110, 1,
            1, 0, 0, 0, 0, UTC);

    /**
     * Prevents other classes from instantiating the class.
     * This is useful when a class wants to control all calls to create new instances of itself.
     */
    private InputAssessment() {
    }

    public static void assess(String algorithmClassName, ZonedDateTime zonedDateTime, double longitude, double latitude,
                              double pressure, double temperature) {

        assessAlgorithmClassName(algorithmClassName);
        assessZonedDateTime(zonedDateTime);
        assessLongitude(longitude);
        assessLatitude(latitude);
        assessPressure(pressure);
        assessTemperature(temperature);
    }

    private static void assessAlgorithmClassName(String algorithmClassName) {
        if (!AlgorithmFactory.isInstance(algorithmClassName)) {
            throw new IllegalArgumentException("Algorithm Class Name Not Found");
        }
    }

    private static void assessZonedDateTime(@NotNull ZonedDateTime zonedDateTime) {

        if (zonedDateTime.withZoneSameInstant(UTC).isBefore(LEFT_BOUND_OF_VALID_TIME_INTERVAL) ||
                zonedDateTime.withZoneSameInstant(UTC).isAfter(RIGHT_BOUND_OF_VALID_TIME_INTERVAL))
            throw new IllegalArgumentException("ZoneDateTime must be between JAN-01-2010[UTC] and JAN-01-2110[UTC]");
    }

    private static void assessLongitude(double longitude) {
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE)
            throw new IllegalArgumentException("Longitude must be between [0, 2PI] rad");
    }

    private static void assessLatitude(double latitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE)
            throw new IllegalArgumentException("Latitude must be between [-PI/2, PI/2] rad");
    }

    private static void assessPressure(double pressure) {
        if (pressure < MIN_PRESSURE || pressure > MAX_PRESSURE)
            throw new IllegalArgumentException("Pressure must be between [0.85862324204293, 1.0696274364668] atm");
    }

    private static void assessTemperature(double temperature) {
        if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE)
            throw new IllegalArgumentException("Temperature must be between [-89.2, 54.0] °C");
    }
}