package net.ddns.starla.fnacsp.template.entities;

public final class Latitude extends Entity {
    public static final double MAX_LATITUDE_RAD = 1.57079632679490;
    public static final double MIN_LATITUDE_RAD = -1.57079632679490;

    /**
     * @param value [-PI/2, PI/2] rad
     */
    public Latitude(double value) {
        super(value);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (isOutOfBounds()) {
            throw new LatitudeException(
                    "Latitude must be between [" + Latitude.MIN_LATITUDE_RAD + ", " + Latitude.MAX_LATITUDE_RAD + "] rad");
        }
    }

    /**
     * @return true, if it is out of bounds.
     */
    private boolean isOutOfBounds() {
        double latitude = (double) value;
        return (latitude < Latitude.MIN_LATITUDE_RAD || latitude > Latitude.MAX_LATITUDE_RAD);
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
    @SuppressWarnings("SameParameterValue")
    LatitudeException(String message) {
        super(message);
    }
}
