package net.ddns.starla.fnacsp.template.entities.factory;

import net.ddns.starla.fnacsp.template.entities.*;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class EntityFactoryTest {

    @Test(expected = EntityFactoryException.class)
    public void createInstanceOfEntity_ShouldThrowEntityFactoryException() {
        EntityFactory.createInstance("Entity", new Object());
    }

    @Test(expected = EntityFactoryException.class)
    public void createInstanceOfUnknownEntity_ShouldThrowEntityFactoryException() {
        EntityFactory.createInstance("UnknownEntity", new Object());
    }

    @Test
    public void createInstanceOfLatitude() {
        assertEquals(Latitude.class,
                EntityFactory.createInstance("Latitude", Latitude.MIN_LATITUDE_RAD).getClass());
    }

    @Test
    public void createInstanceOfLongitude() {
        assertEquals(Longitude.class,
                EntityFactory.createInstance("Longitude", Longitude.MIN_LONGITUDE_RAD).getClass());
    }

    @Test
    public void createInstanceOfPressure() {
        assertEquals(Pressure.class,
                EntityFactory.createInstance("Pressure", Pressure.MIN_PRESS_IN_ATM).getClass());
    }

    @Test
    public void createInstanceOfTemperature() {
        assertEquals(Temperature.class,
                EntityFactory.createInstance("Temperature", Temperature.MIN_TEMP_CELSIUS).getClass());
    }

    @Test
    public void createInstanceOfTime() {
        assertEquals(
                Time.class, EntityFactory.createInstance("Time", Time.BEGINNING_TIME_INTERVAL).getClass());
    }

    @Test
    public void getClasses() {
        String[] classNames = new String[]{"Time", "Longitude", "Latitude", "Temperature", "Pressure"};
        assertArrayEquals(classNames, EntityFactory.getClasses());
    }
}