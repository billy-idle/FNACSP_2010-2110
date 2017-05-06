package net.ddns.starla.pattern.facade;

import net.ddns.starla.pattern.strategy.Algorithm;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class LocalSunPositionTest {

    private LocalSunPosition localSunPosition;

    @Before
    public void setUp() throws Exception {
        localSunPosition = new LocalSunPosition("Europe/Rome", 0.21787, 0.73117, 1.0,
                20.0);
        localSunPosition.computePosition();
    }

    @Test
    public void zenithInRange() throws Exception {
        assertTrue(isInRange(0, Algorithm.PI, localSunPosition.getZenith()));
    }

    private boolean isInRange(double leftBound, double rightBound, double value) {
        return value >= leftBound && value <= rightBound;
    }

    @Test
    public void azimuthInRange() throws Exception {
        assertTrue(isInRange(-Algorithm.PI, Algorithm.PI, localSunPosition.getAzimuth()));
    }

}
