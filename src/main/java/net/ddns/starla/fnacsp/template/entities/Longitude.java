package net.ddns.starla.fnacsp.template.entities;

public final class Longitude extends Entity<Double> {
    public static final double MAX_LONGITUDE_RAD = 6.28318530717959;
    public static final double MIN_LONGITUDE_RAD = 0.0;

    /**
     * @param angle [0, 2PI] rad
     */
    public Longitude(double angle) {
        super(angle);
    }

    /**
     * It throws a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (this.t < Longitude.MIN_LONGITUDE_RAD || this.t > Longitude.MAX_LONGITUDE_RAD) {
            throw new EntityException(
                    "Longitude must be between [" + Longitude.MIN_LONGITUDE_RAD + ", "
                            + Longitude.MAX_LONGITUDE_RAD + "] rad");
        }
    }
}
