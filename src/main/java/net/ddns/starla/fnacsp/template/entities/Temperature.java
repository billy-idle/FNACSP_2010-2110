package net.ddns.starla.fnacsp.template.entities;

/**
 * This class abstracts the Temperature entity
 */
public class Temperature implements Assessment {
    public static final double MIN_VALUE = -89.2;
    public static final double MAX_VALUE = 54.0;
    private final double degrees;

    /**
     * @param degrees Between [-89.2, 54.0] °C
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Lowest_temperatures_ever_recorded">Lowest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Highest_temperatures_ever_recorded">Highest temperatures ever recorded</a>
     */
    public Temperature(double degrees) {
        this.degrees = degrees;
        assesInput();
    }

    @Override
    public void assesInput() {
        if (!isItValid())
            throw new IllegalArgumentException(
                    "Temperature must be between [" + Temperature.MIN_VALUE + ", " + Temperature.MAX_VALUE + "] °C");
    }

    @Override
    public boolean isItValid() {
        return (degrees >= MIN_VALUE && degrees <= MAX_VALUE);
    }

    /**
     * @return degrees
     */
    public double getDegrees() {
        return degrees;
    }
}