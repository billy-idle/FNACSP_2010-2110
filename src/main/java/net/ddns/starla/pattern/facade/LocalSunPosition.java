package net.ddns.starla.pattern.facade;


import net.ddns.starla.pattern.factory.Accuracy;
import net.ddns.starla.pattern.factory.AlgorithmFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LocalSunPosition {
    private SunPosition sunPosition;
    private String zoneId;
    private double longitude;
    private double latitude;
    private double pressure;
    private double temperature;

    public LocalSunPosition(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        this.zoneId = zoneId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pressure = pressure;
        this.temperature = temperature;

        sunPosition = new SunPosition();
    }

    public void computePosition() {
        sunPosition.computePosition(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.now(ZoneId.of(zoneId)), longitude, latitude, pressure, temperature);
    }

    public double getZenith() {
        return sunPosition.getZenith();
    }

    public double getAzimuth() {
        return sunPosition.getAzimuth();
    }

    public boolean isItDay() {
        return sunPosition.isItDay();
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}