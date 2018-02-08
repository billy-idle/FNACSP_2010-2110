package net.ddns.starla.fnacsp.template.algorithms;

import java.time.ZonedDateTime;

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
     * @param zonedDateTime Wrapper of the time's related values.
     * @param longitude     [0, 2PI] rad
     * @param latitude      [-PI/2,PI/2] rad
     * @param pressure      [0.85862324204293, 1.0696274364668] atm
     * @param temperature   Between [-89.2, 54.0] °C
     */
    @Override
    public void compute(ZonedDateTime zonedDateTime, double longitude, double latitude, double pressure, double temperature) {
//        This method is an intentionally-blank override, because the null object design pattern.
    }
}
