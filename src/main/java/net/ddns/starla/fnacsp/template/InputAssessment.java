package net.ddns.starla.fnacsp.template;

import net.ddns.starla.fnacsp.factory.AlgorithmFactory;
import net.ddns.starla.fnacsp.template.algorithms.Algorithm;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

class InputAssessment {

    private static final ZonedDateTime LEFT_BOUND_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2010, 1,
            1, 0, 0, 0, 0, UTC);
    private static final ZonedDateTime RIGHT_BOUND_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2110, 1,
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
        if (longitude < 0.0 || longitude > Algorithm.PI2)
            throw new IllegalArgumentException("Longitude must be between [0, 2PI] rad");
    }

    private static void assessLatitude(double latitude) {
        if (latitude < -Algorithm.PIM || latitude > Algorithm.PIM)
            throw new IllegalArgumentException("Latitude must be between [-PI/2, PI/2] rad");
    }

    private static void assessPressure(double pressure) {
        if (pressure < 0.85862324204293 || pressure > 1.0696274364668)
            throw new IllegalArgumentException("Pressure must be between [0.85862324204293, 1.0696274364668] atm");
    }

    private static void assessTemperature(double temperature) {
        if (temperature < -89.2 || temperature > 54.0)
            throw new IllegalArgumentException("Temperature must be between [-89.2, 54.0] °C");
    }
}