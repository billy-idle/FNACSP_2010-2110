package net.ddns.starla.fnacsp.template;

import net.ddns.starla.fnacsp.factory.AlgorithmFactory;
import net.ddns.starla.fnacsp.template.algorithms.Algorithm;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

class InputAssessment {
    /**
     * Prevents other classes from instantiating the class.
     * This is useful when a class wants to control all calls to create new instances of itself.
     */
    private InputAssessment() {
    }

    public static void assess(String algorithmClassName, int year, int month, int dayOfMonth, int hour, int minute,
                              int second, int nanoOfSecond, String zoneId, double longitude, double latitude,
                              double pressure, double temperature) {

        assessAlgorithmClassName(algorithmClassName);
        assessZonedDateTime(year, month, dayOfMonth, hour, minute, second, nanoOfSecond, zoneId);
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

    private static void assessZonedDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second,
                                            int nanoOfSecond, String zoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond,
                ZoneId.of(zoneId));

        ZonedDateTime leftBoundOfValidInterval = ZonedDateTime.of(2010, 1, 1, 0,
                0, 0, 0, UTC);

        ZonedDateTime rightBoundOfValidInterval = ZonedDateTime.of(2110, 1, 1, 0,
                0, 0, 0, UTC);

        if (zonedDateTime.withZoneSameInstant(UTC).isBefore(leftBoundOfValidInterval) ||
                zonedDateTime.withZoneSameInstant(UTC).isAfter(rightBoundOfValidInterval))
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