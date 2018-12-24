package com.github.guillesup.se;

import com.github.guillesup.fnacsp.facade.SunPosition;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class Ephemeris {
    private final String algorithm;
    private final ChronoUnit timeUnit;
    private final ZonedDateTime end;
    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    private List<SunPosition> sunPositions;
    private ZonedDateTime start;

    Ephemeris(String algorithm, ChronoUnit timeUnit, ZonedDateTime start, ZonedDateTime end,
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
            this.sunPositions.add(new SunPosition(this.algorithm, this.start, this.longitude, this.latitude,
                    this.pressure, this.temperature));
            this.start = this.start.plus(1, this.timeUnit);
        } while (this.start.isBefore(this.end));
    }

    public void compute() {
        sunPositions.parallelStream().forEach(SunPosition::compute);
    }

    double[] getZenith() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getZenith).toArray();
    }

    double[] getAzimuth() {
        return sunPositions.stream().mapToDouble(SunPosition::getAzimuth).toArray();
    }

    double[] getRightAscension() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getRightAscension).toArray();
    }

    double[] getDeclination() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getDeclination).toArray();
    }

    double[] getHourAngle() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getHourAngle).toArray();
    }

    ZonedDateTime[] getTimeInterval() {
        return this.sunPositions.stream().map(SunPosition::getZonedDateTime).toArray(ZonedDateTime[]::new);
    }

    String[] getTimeOfDay() {
        return this.sunPositions.stream().map(SunPosition::getTimeOfDay).toArray(String[]::new);
    }

    double getLongitude() {
        return sunPositions.get(0).getLongitude();
    }

    double getLatitude() {
        return sunPositions.get(0).getLatitude();
    }

    double[] getPressure() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getPressure).toArray();
    }

    double[] getTemperature() {
        return this.sunPositions.stream().mapToDouble(SunPosition::getTemperature).toArray();
    }
}
