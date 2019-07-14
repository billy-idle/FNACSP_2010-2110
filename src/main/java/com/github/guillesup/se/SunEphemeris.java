package com.github.guillesup.se;

import com.github.guillesup.fnacsp.facade.SunPosition;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class SunEphemeris {
    private final String algorithm;
    private final ChronoUnit timeUnit;
    private final ZonedDateTime end;
    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    private List<SunPosition> sunPositions;
    private ZonedDateTime start;

    /**
     * @param zoneId
     * @param longitude
     * @param latitude
     * @param pressure
     * @param temperature
     */
    public SunEphemeris(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        this("AlgorithmFive",
                ChronoUnit.MINUTES,
                ZonedDateTime.now(ZoneId.of(zoneId)).withHour(0).withMinute(0).withSecond(0).withNano(0),
                ZonedDateTime.now(ZoneId.of(zoneId)).withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(1),
                longitude,
                latitude,
                pressure,
                temperature);
    }

    public SunEphemeris(String algorithm, ChronoUnit timeUnit, ZonedDateTime start, ZonedDateTime end,
                        double longitude, double latitude, double pressure, double temperature) {
        this.algorithm = algorithm;
        this.timeUnit = timeUnit;
        this.start = start;
        this.end = end;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pressure = pressure;
        this.temperature = temperature;
        setUpList();
    }

    private void setUpList() {
        this.sunPositions = new ArrayList<>((int) this.timeUnit.between(start, end));

        do {
            this.sunPositions.add(
                    new SunPosition(
                            this.algorithm,
                            this.start,
                            this.longitude,
                            this.latitude,
                            this.pressure,
                            this.temperature));

            this.start = this.start.plus(1, this.timeUnit);

        } while (this.start.isBefore(this.end));
    }

    public void compute() {
        sunPositions.parallelStream().forEach(SunPosition::compute);
    }

    public double[] getZenith() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getZenith).toArray();
    }

    public double[] getAzimuth() {
        return sunPositions.stream().mapToDouble(SunPosition::getAzimuth).toArray();
    }

    public double[] getRightAscension() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getRightAscension).toArray();
    }

    public double[] getDeclination() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getDeclination).toArray();
    }

    public double[] getHourAngle() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getHourAngle).toArray();
    }

    public ZonedDateTime[] getTimeInterval() {
        return this.sunPositions.stream().map(SunPosition::getZonedDateTime).toArray(ZonedDateTime[]::new);
    }

    public String[] getTimeOfDay() {
        return this.sunPositions.stream().map(SunPosition::getTimeOfDay).toArray(String[]::new);
    }

    public double getLongitude() {
        return sunPositions.get(0).getLongitude();
    }

    public double getLatitude() {
        return sunPositions.get(0).getLatitude();
    }

    public double[] getPressure() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getPressure).toArray();
    }

    public double[] getTemperature() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getTemperature).toArray();
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        StringBuilder row = new StringBuilder();

        String desc = String.format("Sun SunEphemeris for %1$s at Long. %3$f and Lat. %4$f on %2$tB %2$te of %2$tY%n%n",
                getTimeInterval()[0].getZone(), getTimeInterval()[0], getLongitude(),
                getLatitude());

        String tableHeader = String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s %8s %8s%n", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "Pres.", "Temp.", "ToD");

        for (int i = 0; i < getTimeInterval().length; i++) {
            row.append(String.format("%tT", getTimeInterval()[i]));
            row.append(String.format("%9.3f", getZenith()[i]));
            row.append(String.format("%12.3f", getAzimuth()[i]));
            row.append(String.format("%12.3f", getRightAscension()[i]));
            row.append(String.format("%15.3f", getDeclination()[i]));
            row.append(String.format("%15.3f", getHourAngle()[i]));
            row.append(String.format("%10.1f", getPressure()[i]));
            row.append(String.format("%10.1f", getTemperature()[i]));
            row.append(String.format("%10s%n", getTimeOfDay()[i]));
        }

        String footer = String.format("%n%s%n%s%n%s%n%s%n", "* angles in radians (rad)",
                "* Pressure in atmospheres (atm)", "* Temperature in Celsius degrees (°C)",
                "* ToD stands for \"Time of Day\"");

        table.append(desc).append(tableHeader).append(row).append(footer);

        return table.toString();
    }
}
