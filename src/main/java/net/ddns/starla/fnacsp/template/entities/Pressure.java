package net.ddns.starla.fnacsp.template.entities;

/**
 * This class abstracts the Pressure entity
 */
public final class Pressure extends Entity {
    public static final double MIN_PRESS_IN_ATM = 0.85862324204293;
    public static final double MAX_PRESS_IN_ATM = 1.0696274364668;

    /**
     * @param value [0.85862324204293, 1.0696274364668] atm
     * @see <a href="https://en.wikipedia.org/wiki/Atmospheric_pressure#Records">Atmospheric pressure records</a>
     */
    public Pressure(double value) {
        super(value);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (isOutOfBounds())
            throw new PressureException(
                    "Pressure must be between [" + Pressure.MIN_PRESS_IN_ATM + ", " + Pressure.MAX_PRESS_IN_ATM + "] atm");

    }

    /**
     * @return true, if it is out of bounds.
     */
    private boolean isOutOfBounds() {
        double press = (double) value;
        return (press < Pressure.MIN_PRESS_IN_ATM || press > Pressure.MAX_PRESS_IN_ATM);
    }
}

class PressureException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    @SuppressWarnings("SameParameterValue")
    PressureException(String message) {
        super(message);
    }
}