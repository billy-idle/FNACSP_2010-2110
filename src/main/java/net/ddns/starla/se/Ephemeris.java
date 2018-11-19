package net.ddns.starla.se;

import net.ddns.starla.fnacsp.facade.SunPosition;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class Ephemeris {
    private List<SunPosition> sunPositions;
    private final String algorithm;
    private final ChronoUnit timeUnit;
    private ZonedDateTime start;
    private final ZonedDateTime end;
    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;

    public Ephemeris(String algorithm, ChronoUnit timeUnit, ZonedDateTime start, ZonedDateTime end,
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

    public void compute() {
        sunPositions.parallelStream().forEach(SunPosition::compute);
    }

    private void setUpList() {
        this.sunPositions = new ArrayList<>((int) this.timeUnit.between(start, end));

        do {
            this.sunPositions.add(new SunPosition(this.algorithm, this.start, this.longitude, this.latitude,
                    this.pressure, this.temperature));
            this.start = this.start.plus(1, this.timeUnit);
        } while (this.start.isBefore(this.end));
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

    public double getLongitude(){
        return sunPositions.get(0).getLongitude();
    }

    public double getLatitude(){
        return sunPositions.get(0).getLatitude();
    }

    public double[] getPressure() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getPressure).toArray();
    }

    public double[] getTemperature(){
        return this.sunPositions.stream().mapToDouble(SunPosition::getTemperature).toArray();
    }
}
