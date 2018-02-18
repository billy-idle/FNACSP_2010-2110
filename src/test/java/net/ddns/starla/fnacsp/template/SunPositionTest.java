package net.ddns.starla.fnacsp.template;

import net.ddns.starla.fnacsp.template.algorithms.Algorithm;
import org.junit.Test;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;


public class SunPositionTest {

    private static final ZonedDateTime LEFT_BOUND_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2010, 1,
            1, 0, 0, 0, 0, UTC);
    private static final ZonedDateTime MIDPOINT_OF_VALID_TIME_INTERVAL = LEFT_BOUND_OF_VALID_TIME_INTERVAL.plusYears(50);
    private static final ZonedDateTime RIGHT_BOUND_OF_VALID_TIME_INTERVAL = ZonedDateTime.of(2110, 1,
            1, 0, 0, 0, 0, UTC);
    private final static String algorithmClassName = "AlgorithmFive";

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeBefore2010_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, LEFT_BOUND_OF_VALID_TIME_INTERVAL.minusNanos(1),
                0.21787, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenZonedDateTimeAfter2110_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, RIGHT_BOUND_OF_VALID_TIME_INTERVAL.plusNanos(1),
                0.21787, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeNegative_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                -1, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLongitudeGreaterThanPI2_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                Algorithm.PI2 + .1, 0.73117, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeLessThanMinusPIM_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, -Algorithm.PIM - .1, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLatitudeGreaterThanMinusPIM_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, Algorithm.PIM + .1, 1.0, 20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureBelowMinRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, 0.73117, 0.85862324204293 - .1,
                20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPressureAboveMaxRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, 0.73117, 1.0696274364668 + .1,
                20.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureBelowMinRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, 0.73117, 1.0, -89.2 - .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemperatureAboveMaxRecord_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName, MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, 0.73117, 1.0, 54.0 + .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAlgorithmClassNameNotFound_ShouldThrowIllegalArgumentException() {
        SunPosition.of(algorithmClassName.concat("2498#$@%^&("), MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, 0.73117, 1.0,
                54.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenClassNameFoundButNotAlgorithmType_ShouldThrowIllegalArgumentException() {
        SunPosition.of("DummyClass", MIDPOINT_OF_VALID_TIME_INTERVAL,
                0.21787, 0.73117, 1.0, 54.0);
    }
}