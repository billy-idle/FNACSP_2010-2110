package net.ddns.starla.fnacsp.template.algorithms.factory;

import net.ddns.starla.fnacsp.template.algorithms.*;
import net.ddns.starla.fnacsp.template.entities.*;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AlgorithmFactoryTest {
    private final ZonedDateTime zonedDateTime = Time.BEGINNING_TIME_INTERVAL;
    private final double longitude = Longitude.MIN_LONGITUDE_RAD;
    private final double latitude = Latitude.MIN_LATITUDE_RAD;
    private final double pressure = Pressure.MIN_PRESS_IN_ATM;
    private final double temperature = Temperature.MIN_TEMP_CELSIUS;

    @Test(expected = AlgorithmFactoryException.class)
    public void createInstanceOfAlgorithm_ShouldTrowAlgorithmFactoryException() {
        assertEquals(Algorithm.class, AlgorithmFactory.createInstance("Algorithm",
                zonedDateTime, longitude, latitude, pressure, temperature).getClass());
    }

    @Test
    public void createInstanceOfAlgorithmOne() {
        assertEquals(AlgorithmOne.class, AlgorithmFactory.createInstance("AlgorithmOne",
                zonedDateTime, longitude, latitude, pressure, temperature).getClass());
    }

    @Test
    public void createInstanceOfAlgorithmTwo() {
        assertEquals(AlgorithmTwo.class, AlgorithmFactory.createInstance("AlgorithmTwo",
                zonedDateTime, longitude, latitude, pressure, temperature).getClass());
    }

    @Test
    public void createInstanceOfAlgorithmThree() {
        assertEquals(AlgorithmThree.class, AlgorithmFactory.createInstance("AlgorithmThree",
                zonedDateTime, longitude, latitude, pressure, temperature).getClass());
    }

    @Test
    public void createInstanceOfAlgorithmFour() {
        assertEquals(AlgorithmFour.class, AlgorithmFactory.createInstance("AlgorithmFour",
                zonedDateTime, longitude, latitude, pressure, temperature).getClass());
    }

    @Test
    public void createInstanceOfAlgorithmFive() {
        assertEquals(AlgorithmFive.class, AlgorithmFactory.createInstance("AlgorithmFive",
                zonedDateTime, longitude, latitude, pressure, temperature).getClass());
    }

    @Test(expected = AlgorithmFactoryException.class)
    public void createInstanceOfUnknownAlgorithm_ShouldThrowAlgorithmFactoryException() {
        AlgorithmFactory.createInstance("UnknownAlgorithm",
                zonedDateTime, longitude, latitude, pressure, temperature);
    }

    @Test
    public void getClasses() {
        String[] classNames =
                new String[]{"AlgorithmOne", "AlgorithmTwo", "AlgorithmThree", "AlgorithmFour", "AlgorithmFive"};
        assertArrayEquals(classNames, AlgorithmFactory.getClassNames());
    }
}