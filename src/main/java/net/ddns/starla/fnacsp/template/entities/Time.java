package net.ddns.starla.fnacsp.template.entities;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

/**
 * This class abstracts the Time entity
 */
public final class Time extends Entity<ZonedDateTime> {
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
        if (this.t.withZoneSameInstant(UTC).isBefore(Time.BEGINNING_TIME_INTERVAL) ||
                this.t.withZoneSameInstant(UTC).isAfter(Time.END_TIME_INTERVAL)) {
            throw new EntityException(
                    "ZoneDateTime must be between" + Time.BEGINNING_TIME_INTERVAL + " and " +
                            Time.END_TIME_INTERVAL);
        }

    }
}