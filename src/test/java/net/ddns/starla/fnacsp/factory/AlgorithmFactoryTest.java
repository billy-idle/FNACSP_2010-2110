package net.ddns.starla.fnacsp.factory;

import net.ddns.starla.fnacsp.template.algorithms.*;
import net.ddns.starla.fnacsp.template.entities.AtmPressure;
import net.ddns.starla.fnacsp.template.entities.Coordinates;
import net.ddns.starla.fnacsp.template.entities.Temperature;
import net.ddns.starla.fnacsp.template.entities.Time;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class AlgorithmFactoryTest {
    private static AlgorithmFactory algorithmFactory;
    private ZonedDateTime zonedDateTime = Time.BEGINNING_OF_VALID_TIME_INTERVAL;
    private double longitude = Coordinates.MIN_LONGITUDE_VALUE;
    private double latitude = Coordinates.MIN_LATITUDE_VALUE;
    private double pressure = AtmPressure.MIN_VALUE;
    private double temperature = Temperature.MIN_VALUE;

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
}