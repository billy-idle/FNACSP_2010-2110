package com.rg.solar.fnacsp.template.entities;

/**
 * This class abstracts the Temperature entity
 */
public final class Temperature extends Entity<Double> {
    public static final double MIN_TEMP_CELSIUS = -89.2;
    public static final double MAX_TEMP_CELSIUS = 55.0;

    /**
     * @param celsius Between [-89.2, 55.0] °C
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Lowest_temperatures_ever_recorded">
     * Lowest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Highest_temperatures_ever_recorded">
     * Highest temperatures ever recorded</a>
     */
    public Temperature(double celsius) {
        super(celsius);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (this.t < Temperature.MIN_TEMP_CELSIUS || this.t > Temperature.MAX_TEMP_CELSIUS)
            throw new EntityException(
                    "Temperature must be between [" + Temperature.MIN_TEMP_CELSIUS + ", "
                            + Temperature.MAX_TEMP_CELSIUS + "] °C");
    }
}