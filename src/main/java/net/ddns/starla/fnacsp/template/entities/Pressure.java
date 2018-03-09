package net.ddns.starla.fnacsp.template.entities;

/**
 * This class abstracts the Pressure entity
 */
public final class Pressure extends Entity {
    public static final double MIN_VALUE = 0.85862324204293;
    public static final double MAX_VALUE = 1.0696274364668;

    /**
     * @param magnitude [0.85862324204293, 1.0696274364668] atm
     * @see <a href="https://en.wikipedia.org/wiki/Atmospheric_pressure#Records">Atmospheric pressure records</a>
     */
    public Pressure(double magnitude) {
        super(magnitude);
    }

    /**
     * It should throw a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (!isItValid())
            throw new PressureException(
                    "Pressure must be between [" + Pressure.MIN_VALUE + ", " + Pressure.MAX_VALUE + "] atm");

    }


    /**
     * @return true, if it belongs to the valid interval or range.
     */
    private boolean isItValid() {
        return ((double) value >= MIN_VALUE && (double) value <= MAX_VALUE);
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
    PressureException(String message) {
        super(message);
    }
}