package net.ddns.starla.fnacsp.pattern.strategy;

public class AlgorithmFactory {
    /**
     * @param accuracy The allowed values are: LOWEST, LOW, MID, HIGH, HIGHEST
     * @return An instance of Algorithm class
     */
    public Algorithm getInstance(Accuracy accuracy) {
        switch (accuracy) {
            case LOWEST:
                return new Algorithm_1();
            case LOW:
                return new Algorithm_2();
            case MID:
                return new Algorithm_3();
            case HIGH:
                return new Algorithm_4();
            case HIGHEST:
                return new Algorithm_5();
            default:
                return new Algorithm_1();
        }
    }
}
