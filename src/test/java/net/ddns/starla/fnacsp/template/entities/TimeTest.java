package net.ddns.starla.fnacsp.template.entities;

import org.junit.Test;

public class TimeTest {

    @Test(expected = TimeException.class)
    public void whenBeforeBeginningTimeInterval_ShouldThrowTimeException() {
        new Time(Time.BEGINNING_TIME_INTERVAL.minusNanos(1));
    }

    @Test(expected = TimeException.class)
    public void whenAfterEndTimeInterval_ShouldThrowTimeException() {
        new Time(Time.END_TIME_INTERVAL.plusNanos(1));
    }
}