package net.ddns.starla.fnacsp.template.algorithms;

/**
 * Null Object Design Pattern
 */
public class NullAlgorithm extends Algorithm {

    private static NullAlgorithm nullAlgorithm;

    private NullAlgorithm() {
    }

    public static synchronized NullAlgorithm getInstance() {

        if (nullAlgorithm == null) {
            nullAlgorithm = new NullAlgorithm();
        }

        return nullAlgorithm;
    }

    /**
     * This method is an intentionally-blank override, because the null object design pattern.
     *
     * @param longitude [0, 2PI] rad
     */
    @Override
    public void accuracyLevel(double longitude) {
        // This method is an intentionally-blank override, because the null object design pattern.
    }
}
