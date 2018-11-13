package net.ddns.starla.fnacsp.template.entities;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

/**
 * This class abstracts the Time entity
 */
public final class Time extends Entity {
    public static final ZonedDateTime BEGINNING_TIME_INTERVAL = ZonedDateTime.of(2010, 1,
            1, 0, 0, 0, 0, UTC);
    public static final ZonedDateTime END_TIME_INTERVAL = ZonedDateTime.of(2110, 1,
            1, 0, 0, 0, 0, UTC);

    /**
     * @param zonedDateTime From 2010 to 2110 at UTC
     */
    public Time(ZonedDateTime zonedDateTime) {
        super(zonedDateTime);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (isOutOfBounds()) {
            throw new TimeException(
                    "ZoneDateTime must be between" + Time.BEGINNING_TIME_INTERVAL + " and " +
                            Time.END_TIME_INTERVAL);
        }

    }

    /**
     * @return true, if it is out of bounds.
     */
    private boolean isOutOfBounds() {
        ZonedDateTime zdt = (ZonedDateTime) value;

        return zdt.withZoneSameInstant(UTC).isBefore(Time.BEGINNING_TIME_INTERVAL) ||
                zdt.withZoneSameInstant(UTC).isAfter(Time.END_TIME_INTERVAL);
    }
}

class TimeException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    TimeException(String message) {
        super(message);
    }
}
