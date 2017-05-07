package net.ddns.starla.fnacsp.pattern.facade;

import net.ddns.starla.fnacsp.pattern.strategy.Algorithm;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class SunPositionNowTest {

    private SunPositionNow sunPositionNow;

    @Before
    public void setUp() throws Exception {
        sunPositionNow = new SunPositionNow("Europe/Rome", 0.21787, 0.73117, 1.0,
                20.0);
    }

    @Test
    public void zenithInRange() throws Exception {
        assertTrue(isInRange(0, Algorithm.PI, sunPositionNow.getZenith()));
    }

    private boolean isInRange(double leftBound, double rightBound, double value) {
        return value >= leftBound && value <= rightBound;
    }

    @Test
    public void azimuthInRange() throws Exception {
        assertTrue(isInRange(-Algorithm.PI, Algorithm.PI, sunPositionNow.getAzimuth()));
    }

}
