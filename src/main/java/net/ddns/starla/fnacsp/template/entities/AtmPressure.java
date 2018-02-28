package net.ddns.starla.fnacsp.template.entities;

/**
 * This class abstracts the Pressure entity
 */
public class AtmPressure implements Assessment {
    public static final double MIN_VALUE = 0.85862324204293;
    public static final double MAX_VALUE = 1.0696274364668;
    private final double pressure;

    /**
     * @param pressure [0.85862324204293, 1.0696274364668] atm
     * @see <a href="https://en.wikipedia.org/wiki/Atmospheric_pressure#Records">Atmospheric pressure records</a>
     */
    public AtmPressure(double pressure) {
        this.pressure = pressure;
        assesInput();
    }

    @Override
    public void assesInput() {
        if (!isItValid())
            throw new IllegalArgumentException(
                    "Pressure must be between [" + AtmPressure.MIN_VALUE + ", " + AtmPressure.MAX_VALUE + "] atm");

    }

    @Override
    public boolean isItValid() {
        return (pressure >= MIN_VALUE && pressure <= MAX_VALUE);
    }

    /**
     * @return pressure
     */
    public double getPressure() {
        return pressure;
    }
}