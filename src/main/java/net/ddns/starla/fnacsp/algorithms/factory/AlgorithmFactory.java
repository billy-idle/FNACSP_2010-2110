package net.ddns.starla.fnacsp.algorithms.factory;

import net.ddns.starla.fnacsp.algorithms.strategy.Algorithm;
import net.ddns.starla.fnacsp.algorithms.strategy.NullAlgorithm;

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
            if (Algorithm.class.isInstance(Class.forName(canonicalName).newInstance())) {
                return (Algorithm) Class.forName(canonicalName).newInstance();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            return NullAlgorithm.getInstance();
        }

        return NullAlgorithm.getInstance();
    }
}
