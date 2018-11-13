package net.ddns.starla.fnacsp.template.entities;

/**
 * This class abstracts the Temperature entity
 */
public final class Temperature extends Entity {
    public static final double MIN_TEMP_CELSIUS = -89.2;
    public static final double MAX_TEMP_CELSIUS = 54.0;

    /**
     * @param value Between [-89.2, 54.0] °C
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Lowest_temperatures_ever_recorded">Lowest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Highest_temperatures_ever_recorded">Highest temperatures ever recorded</a>
     */
    public Temperature(double value) {
        super(value);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (isOutOfBounds())
            throw new TemperatureException(
                    "Temperature must be between [" + Temperature.MIN_TEMP_CELSIUS + ", " + Temperature.MAX_TEMP_CELSIUS + "] °C");
    }

    /**
     * @return true, if it is out of bounds.
     */
    private boolean isOutOfBounds() {
        double temp = (double) value;
        return (temp < Temperature.MIN_TEMP_CELSIUS || temp > Temperature.MAX_TEMP_CELSIUS);
    }
}

class TemperatureException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    @SuppressWarnings("SameParameterValue")
    TemperatureException(String message) {
        super(message);
    }
}