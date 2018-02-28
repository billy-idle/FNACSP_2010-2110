package net.ddns.starla.fnacsp.factory;

import net.ddns.starla.fnacsp.template.algorithms.Algorithm;
import net.ddns.starla.fnacsp.template.entities.AtmPressure;
import net.ddns.starla.fnacsp.template.entities.Coordinates;
import net.ddns.starla.fnacsp.template.entities.Temperature;
import net.ddns.starla.fnacsp.template.entities.Time;

import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;

/**
 * Factory Object Pattern
 */
public class AlgorithmFactory {
    /**
     * Prevents other classes from instantiating the class.
     * This is useful when a class wants to control all calls to create new instances of itself.
     */
    private AlgorithmFactory() {
    }

    /**
     * @param algorithmClassName Valid names are any Algorithm subclass
     * @param zonedDateTime      From 2010 to 2110 at UTC
     * @param longitude          [0, 2PI] rad
     * @param latitude           [-PI/2,PI/2] rad
     * @param pressure           [0.85862324204293, 1.0696274364668] atm
     * @param temperature        [-89.2, 54.0] °C
     * @return A concrete instance of Algorithm subclass
     */
    public static Algorithm createInstance(String algorithmClassName, ZonedDateTime zonedDateTime, double longitude,
                                           double latitude, double pressure, double temperature) {
        String canonicalName = Algorithm.class.getPackage().getName() + "." + algorithmClassName;

        try {
            Object object = Class.forName(canonicalName).getConstructor(Time.class, Coordinates.class,
                    AtmPressure.class, Temperature.class).newInstance(new Time(zonedDateTime),
                    new Coordinates(longitude, latitude), new AtmPressure(pressure), new Temperature(temperature));
            if (Algorithm.class.isInstance(object)) {
                return (Algorithm) object;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                InvocationTargetException e) {
            throw new IllegalArgumentException("Algorithm Class Name Not Found");
        }

        throw new IllegalArgumentException("Is not an Algorithm subclass");
    }
}
