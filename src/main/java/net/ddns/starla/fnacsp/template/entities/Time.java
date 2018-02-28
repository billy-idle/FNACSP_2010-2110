package net.ddns.starla.fnacsp.template.entities;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

/**
 * This class abstracts the Time entity
 */
public class Time implements Assessment {
    public static final ZonedDateTime BEGINNING_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2010, 1,
            1, 0, 0, 0, 0, UTC);
    public static final ZonedDateTime MIDPOINT_OF_VALID_TIME_INTERVAL = BEGINNING_OF_VALID_TIME_INTERVAL.plusYears(50);
    public static final ZonedDateTime END_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2110, 1,
            1, 0, 0, 0, 0, UTC);

    private final ZonedDateTime zonedDateTime;

    /**
     * @param zonedDateTime From 2010 to 2110 at UTC
     */
    public Time(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
        assesInput();
    }

    @Override
    public void assesInput() {
        if (!isItValid()) {
            throw new IllegalArgumentException(
                    "ZoneDateTime must be between" + Time.BEGINNING_OF_VALID_TIME_INTERVAL + " and " +
                            Time.END_OF_VALID_TIME_INTERVAL);
        }

    }

    @Override
    public boolean isItValid() {
        return (zonedDateTime.withZoneSameInstant(UTC).isEqual(BEGINNING_OF_VALID_TIME_INTERVAL)) ||
                (zonedDateTime.withZoneSameInstant(UTC).isEqual(END_OF_VALID_TIME_INTERVAL)) ||
                (zonedDateTime.withZoneSameInstant(UTC).isAfter(BEGINNING_OF_VALID_TIME_INTERVAL) &&
                        (zonedDateTime.withZoneSameInstant(UTC).isBefore(END_OF_VALID_TIME_INTERVAL)));
    }

    /**
     * @return zoneDateTime
     */
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
