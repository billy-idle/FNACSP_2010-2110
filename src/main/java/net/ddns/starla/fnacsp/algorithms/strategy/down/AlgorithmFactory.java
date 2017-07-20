package net.ddns.starla.fnacsp.algorithms.strategy.down;

import net.ddns.starla.fnacsp.algorithms.strategy.top.Algorithm;

public final class AlgorithmFactory {

    /**
     * Prevents other classes from instantiating the class.
     * This is useful when a class only has static methods.
     */
    private AlgorithmFactory() {
    }

    /**
     * @param accuracy The allowed values are: LOWEST, LOW, MID, HIGH, HIGHEST
     * @return An instance of Algorithm class
     */
    public static Algorithm getInstance(Accuracy accuracy) {
        switch (accuracy) {
            case LOWEST:
                return new AlgorithmOne();
            case LOW:
                return new AlgorithmTwo();
            case MID:
                return new AlgorithmThree();
            case HIGH:
                return new AlgorithmFour();
            case HIGHEST:
                return new AlgorithmFive();
            default:
                return new AlgorithmOne();
        }
    }
}