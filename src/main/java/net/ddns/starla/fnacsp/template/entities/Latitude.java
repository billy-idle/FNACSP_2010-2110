package net.ddns.starla.fnacsp.template.entities;

public final class Latitude extends Entity {
    public static final double MAX_LATITUDE_VALUE = 1.57079632679490;
    public static final double MIN_LATITUDE_VALUE = -1.57079632679490;

    /**
     * @param angle [-PI/2, PI/2] rad
     */
    public Latitude(double angle) {
        super(angle);
    }

    /**
     * It should throw a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (!isItValid()) {
            throw new LatitudeException(
                    "Latitude must be between [" + MIN_LATITUDE_VALUE + ", " + MAX_LATITUDE_VALUE + "] rad");
        }
    }

    /**
     * @return true, if it belongs to the valid interval or range.
     */
    private boolean isItValid() {
        return ((double) value >= MIN_LATITUDE_VALUE && (double) value <= MAX_LATITUDE_VALUE);
    }
}

class LatitudeException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    LatitudeException(String message) {
        super(message);
    }
}
