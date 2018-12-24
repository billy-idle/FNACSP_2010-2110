package com.github.guillesup.fnacsp.template.entities.factory;

import com.github.guillesup.fnacsp.template.entities.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;

/**
 * Factory Object Pattern
 */
public class EntityFactory {

    /**
     * Prevents other classes from instantiating the class.
     * This is useful when a class wants to control all calls to create new instances of itself.
     */
    private EntityFactory() {
    }

    /**
     * @param entity Any Entity subclass
     * @param value  double or ZonedDateTime
     * @return An instance of Entity subclass
     */
    public static Entity createInstance(String entity, Object value) {
        String canonicalName = Entity.class.getPackage().getName() + "." + entity;
        Constructor constructor;

        try {
            constructor = Class.forName(canonicalName).getConstructor(double.class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            try {
                constructor = Class.forName(canonicalName).getConstructor(ZonedDateTime.class);
            } catch (NoSuchMethodException e1) {
                throw new EntityFactoryException("Entity Class constructor not found: " + e1);
            } catch (ClassNotFoundException e1) {
                throw new EntityFactoryException("Entity Class name not found: " + e1);
            }
        }

        Object object;

        try {
            object = constructor.newInstance(value);
        } catch (IllegalAccessException e) {
            throw new EntityFactoryException("Illegal Access Exception: " + e);
        } catch (InstantiationException e) {
            throw new EntityFactoryException("Instantiation Exception: " + e);
        } catch (InvocationTargetException e) {
            throw new EntityFactoryException("*Check the input value!* Invocation Target Exception: " + e);
        }

        if (object instanceof Entity) {
            return (Entity) object;
        }

        throw new EntityFactoryException("Is not an Entity subclass");
    }

    /**
     * Returns an array with the valid entities classes' names
     */
    public static String[] getClasses() {
        return new String[]{"Time", "Longitude", "Latitude", "Temperature", "Pressure"};
    }
}

class EntityFactoryException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    EntityFactoryException(String message) {
        super(message);
    }
}
