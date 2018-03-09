package net.ddns.starla.fnacsp.template.entities;

public final class Longitude extends Entity {
    public static final double MAX_LONGITUDE_VALUE = 6.28318530717959;
    public static final double MIN_LONGITUDE_VALUE = 0.0;

    /**
     * @param angle [0, 2PI] rad
     */
    public Longitude(double angle) {
        super(angle);
    }

    /**
     * It should throw a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (!isItValid()) {
            throw new LongitudeException(
                    "Longitude must be between [" + MIN_LONGITUDE_VALUE + ", " + MAX_LONGITUDE_VALUE + "] rad");

        }
    }

    /**
     * @return true, if it belongs to the valid interval or range.
     */
    private boolean isItValid() {
        return ((double) value >= MIN_LONGITUDE_VALUE && (double) value <= MAX_LONGITUDE_VALUE);
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
    LongitudeException(String message) {
        super(message);
    }
}
