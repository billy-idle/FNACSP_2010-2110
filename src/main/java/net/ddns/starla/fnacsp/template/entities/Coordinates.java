package net.ddns.starla.fnacsp.template.entities;

/**
 * This class abstracts the Coordinates entity
 */
public class Coordinates implements Assessment {
    public static final double MAX_LONGITUDE_VALUE = 6.28318530717959;
    public static final double MIN_LONGITUDE_VALUE = 0.0;
    public static final double MAX_LATITUDE_VALUE = 1.57079632679490;
    public static final double MIN_LATITUDE_VALUE = -1.57079632679490;
    private final double longitude;
    private final double latitude;

    /**
     * @param longitude [0, 2PI] rad
     * @param latitude  [-PI/2, PI/2] rad
     */
    public Coordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        assesInput();
    }

    @Override
    public void assesInput() {
        if (!isItValid()) {
            throw new IllegalArgumentException(
                    "Longitude must be between [" + Coordinates.MIN_LONGITUDE_VALUE + ", " +
                            Coordinates.MAX_LONGITUDE_VALUE + "] rad and " +
                            "Latitude must be between [" + Coordinates.MIN_LATITUDE_VALUE + ", " +
                            Coordinates.MAX_LATITUDE_VALUE + "] rad");
        }
    }

    @Override
    public boolean isItValid() {
        return (longitude >= MIN_LONGITUDE_VALUE && longitude <= MAX_LONGITUDE_VALUE)
                && (latitude >= MIN_LATITUDE_VALUE && latitude <= MAX_LATITUDE_VALUE);
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }
}
