package net.ddns.starla.fnacsp.algorithms.factory;

import net.ddns.starla.fnacsp.algorithms.strategy.Algorithm;
import net.ddns.starla.fnacsp.algorithms.strategy.AlgorithmFive;

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
        Class aClass;
        Algorithm algorithm;

        try {
            aClass = Class.forName(canonicalName);
            algorithm = (Algorithm) aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return new AlgorithmFive();
        }

        return algorithm;
    }
}
