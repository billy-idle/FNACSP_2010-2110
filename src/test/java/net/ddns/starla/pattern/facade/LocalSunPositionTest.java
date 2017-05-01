package net.ddns.starla.pattern.facade;

import net.ddns.starla.pattern.strategy.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

public class LocalSunPositionTest {

    private LocalSunPosition localSunPosition;

    @BeforeEach
    public void setUp() throws Exception {
        localSunPosition = new LocalSunPosition();
        localSunPosition.computePosition("Europe/Rome", 0.21787, 0.73117, 1.0, 20.0);
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
