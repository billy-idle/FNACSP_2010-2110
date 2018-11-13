package net.ddns.starla.fnacsp.template.entities;

public final class Longitude extends Entity {
    public static final double MAX_LONGITUDE_RAD = 6.28318530717959;
    public static final double MIN_LONGITUDE_RAD = 0.0;

    /**
     * @param value [0, 2PI] rad
     */
    public Longitude(double value) {
        super(value);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (isOutOfBounds()) {
            throw new LongitudeException(
                    "Longitude must be between [" + Longitude.MIN_LONGITUDE_RAD + ", " + Longitude.MAX_LONGITUDE_RAD + "] rad");

        }
    }

    /**
     * @return true, if it is out of bounds.
     */
    private boolean isOutOfBounds() {
        double longitude = (double) value;
        return (longitude < Longitude.MIN_LONGITUDE_RAD || longitude > Longitude.MAX_LONGITUDE_RAD);
    }
}

class LongitudeException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    @SuppressWarnings("SameParameterValue")
    LongitudeException(String message) {
        super(message);
    }
}
