package net.ddns.starla.fnacsp.algorithms.strategy;

import java.time.ZonedDateTime;

/**
 * NULL Object Pattern
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

    @Override
    public void compute(ZonedDateTime zonedDateTime, double longitude, double latitude, double pressure, double temperature) {

    }
}
