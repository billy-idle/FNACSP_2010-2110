package net.ddns.starla.fnacsp.algorithms;

import org.junit.Test;

import java.time.zone.ZoneRulesException;


public class SunPositionNowTest {

    @Test(expected = ZoneRulesException.class)
    public void whenZoneIdUnknown_ShouldThrowZoneRulesException() {
        new SunPositionNow("AlgorithmOne", "Europe/Bogota", 0.21787, 0.73117, 1.0, 20.0).computePosition();
    }

}
