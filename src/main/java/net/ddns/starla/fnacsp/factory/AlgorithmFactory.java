package net.ddns.starla.fnacsp.factory;

import net.ddns.starla.fnacsp.template.algorithms.Algorithm;
import net.ddns.starla.fnacsp.template.algorithms.NullAlgorithm;

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
     * @return A concrete instance of Algorithm subclass
     */
    public static Algorithm createInstance(String algorithmClassName) {
        String canonicalName = Algorithm.class.getPackage().getName() + "." + algorithmClassName;

        try {
            Object object = Class.forName(canonicalName).newInstance();
            if (Algorithm.class.isInstance(object)) {
                return (Algorithm) object;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            return NullAlgorithm.getInstance();
        }

        return NullAlgorithm.getInstance();
    }

    public static boolean isInstance(String algorithmClassName) {
        String canonicalName = Algorithm.class.getPackage().getName() + "." + algorithmClassName;

        try {
            Object object = Class.forName(canonicalName).newInstance();
            if (Algorithm.class.isInstance(object)) {
                return true; //algorithmClassName is an Algorithm subtype
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            return false; //Algorithm Class Name Not Found
        }

        return false;
    }
}
