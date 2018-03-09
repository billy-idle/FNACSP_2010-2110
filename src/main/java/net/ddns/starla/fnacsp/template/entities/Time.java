package net.ddns.starla.fnacsp.template.entities;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

/**
 * This class abstracts the Time entity
 */
public final class Time extends Entity {
    public static final ZonedDateTime BEGINNING_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2010, 1,
            1, 0, 0, 0, 0, UTC);
    public static final ZonedDateTime END_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2110, 1,
            1, 0, 0, 0, 0, UTC);

    /**
     * @param zonedDateTime From 2010 to 2110 at UTC
     */
    public Time(ZonedDateTime zonedDateTime) {
        super(zonedDateTime);
    }

    /**
     * It should throw a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (!isItValid()) {
            throw new TimeException(
                    "ZoneDateTime must be between" + Time.BEGINNING_OF_VALID_TIME_INTERVAL + " and " +
                            Time.END_OF_VALID_TIME_INTERVAL);
        }

    }

    /**
     * @return true, if it belongs to the valid interval or range.
     */
    private boolean isItValid() {
        ZonedDateTime zonedDateTime = (ZonedDateTime) value;

        return (zonedDateTime.withZoneSameInstant(UTC).isEqual(BEGINNING_OF_VALID_TIME_INTERVAL)) ||
                (zonedDateTime.withZoneSameInstant(UTC).isEqual(END_OF_VALID_TIME_INTERVAL)) ||
                (zonedDateTime.withZoneSameInstant(UTC).isAfter(BEGINNING_OF_VALID_TIME_INTERVAL) &&
                        (zonedDateTime.withZoneSameInstant(UTC).isBefore(END_OF_VALID_TIME_INTERVAL)));
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
