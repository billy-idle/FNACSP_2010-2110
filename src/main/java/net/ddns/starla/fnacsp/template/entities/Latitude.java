package net.ddns.starla.fnacsp.template.entities;

public final class Latitude extends Entity<Double> {
    public static final double MAX_LATITUDE_RAD = 1.57079632679490;
    public static final double MIN_LATITUDE_RAD = -1.57079632679490;

    /**
     * @param angle [-PI/2, PI/2] rad
     */
    public Latitude(double angle) {
        super(angle);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (this.t < Latitude.MIN_LATITUDE_RAD || this.t > Latitude.MAX_LATITUDE_RAD) {
            throw new EntityException(
                    "Latitude must be between [" + Latitude.MIN_LATITUDE_RAD + ", "
                            + Latitude.MAX_LATITUDE_RAD + "] rad");
        }
    }
}