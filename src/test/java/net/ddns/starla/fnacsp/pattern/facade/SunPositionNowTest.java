package net.ddns.starla.fnacsp.pattern.facade;

import net.ddns.starla.fnacsp.facade.SunPositionNow;
import org.junit.Test;

import java.time.zone.ZoneRulesException;


public class SunPositionNowTest {

    @Test(expected = ZoneRulesException.class)
    public void whenZoneIdUnknown_ShouldThrowZoneRulesException() throws Exception {
        new SunPositionNow("Europe/Bogota", 0.21787, 0.73117, 1.0, 20.0).computePosition();
    }

}
