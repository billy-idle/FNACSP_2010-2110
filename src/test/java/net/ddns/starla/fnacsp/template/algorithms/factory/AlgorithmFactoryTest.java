package net.ddns.starla.fnacsp.template.algorithms.factory;

import net.ddns.starla.fnacsp.template.algorithms.*;
import net.ddns.starla.fnacsp.template.entities.*;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AlgorithmFactoryTest {
    private final ZonedDateTime zonedDateTime = Time.BEGINNING_OF_VALID_TIME_INTERVAL;
    private final double longitude = Longitude.MIN_LONGITUDE_VALUE;
    private final double latitude = Latitude.MIN_LATITUDE_VALUE;
    private final double pressure = Pressure.MIN_VALUE;
    private final double temperature = Temperature.MIN_VALUE;

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

    @Test
    public void getClasses() {
        String[] classes = new String[]{"AlgorithmOne", "AlgorithmTwo", "AlgorithmThree", "AlgorithmFour", "AlgorithmFive"};
        assertArrayEquals(classes, AlgorithmFactory.getClasses());
    }
}