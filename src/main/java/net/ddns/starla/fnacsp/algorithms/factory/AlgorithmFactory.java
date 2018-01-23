package net.ddns.starla.fnacsp.algorithms.factory;

import net.ddns.starla.fnacsp.algorithms.strategy.Algorithm;
import net.ddns.starla.fnacsp.algorithms.strategy.NullAlgorithm;

/**
 * Factory Object Pattern
 */
public class AlgorithmFactory {

    private final StringBuilder packageName;

    public AlgorithmFactory() {
        this.packageName = new StringBuilder(Algorithm.class.getPackage().getName() + ".");
    }

    /**
     * @param algorithmClassName Valid names are any Algorithm subclass
     * @return A concrete instance of Algorithm subclass
     */
    public Algorithm createInstance(String algorithmClassName) {
        String canonicalName = packageName.append(algorithmClassName).toString();

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
}
