package com.github.guillesup.fnacsp.template.entities;

/**
 * This class abstracts the Pressure entity
 */
public final class Pressure extends Entity<Double> {
    public static final double MIN_PRESS_IN_ATM = 0.85862324204293;
    public static final double MAX_PRESS_IN_ATM = 1.0696274364668;

    /**
     * @param atm [0.85862324204293, 1.0696274364668] atm
     * @see <a href="https://en.wikipedia.org/wiki/Atmospheric_pressure#Records">Atmospheric pressure records</a>
     */
    public Pressure(double atm) {
        super(atm);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (this.t < Pressure.MIN_PRESS_IN_ATM || this.t > Pressure.MAX_PRESS_IN_ATM)
            throw new EntityException(
                    "Pressure must be between [" + Pressure.MIN_PRESS_IN_ATM + ", "
                            + Pressure.MAX_PRESS_IN_ATM + "] atm");

    }
}