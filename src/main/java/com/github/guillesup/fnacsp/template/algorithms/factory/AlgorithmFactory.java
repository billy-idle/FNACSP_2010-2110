package com.github.guillesup.fnacsp.template.algorithms.factory;

import com.github.guillesup.fnacsp.template.algorithms.Algorithm;
import com.github.guillesup.fnacsp.template.entities.Entity;
import com.github.guillesup.fnacsp.template.entities.factory.EntityFactory;

import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;

/**
 * Factory Object Pattern
 */
public final class AlgorithmFactory {

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
        Object object;

        try {
            object = Class.forName(canonicalName).
                    getConstructor(Entity.class, Entity.class, Entity.class, Entity.class, Entity.class).
                    newInstance(EntityFactory.createInstance("Time", zonedDateTime),
                            EntityFactory.createInstance("Longitude", longitude),
                            EntityFactory.createInstance("Latitude", latitude),
                            EntityFactory.createInstance("Pressure", pressure),
                            EntityFactory.createInstance("Temperature", temperature));
        } catch (ClassNotFoundException e) {
            throw new AlgorithmFactoryException("Algorithm class name not found: " + e);
        } catch (IllegalAccessException e) {
            throw new AlgorithmFactoryException("Illegal Access Exception: " + e);
        } catch (InstantiationException e) {
            throw new AlgorithmFactoryException("Instantiation Exception: " + e);
        } catch (NoSuchMethodException e) {
            throw new AlgorithmFactoryException("Algorithm class constructor not found: " + e);
        } catch (InvocationTargetException e) {
            throw new AlgorithmFactoryException("Invocation Target Exception: " + e);
        }

        if (object instanceof Algorithm) {
            return (Algorithm) object;
        }

        throw new AlgorithmFactoryException("Is not an Algorithm subclass");
    }

    /**
     * Returns an array with the valid algorithms classes' names
     */
    static String[] getClassNames() {
        return new String[]{"AlgorithmOne", "AlgorithmTwo", "AlgorithmThree", "AlgorithmFour", "AlgorithmFive"};
    }
}

class AlgorithmFactoryException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    AlgorithmFactoryException(String message) {
        super(message);
    }
}