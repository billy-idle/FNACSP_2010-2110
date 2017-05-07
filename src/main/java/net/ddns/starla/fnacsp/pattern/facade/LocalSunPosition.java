package net.ddns.starla.fnacsp.pattern.facade;


import net.ddns.starla.fnacsp.pattern.factory.Accuracy;
import net.ddns.starla.fnacsp.pattern.factory.AlgorithmFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LocalSunPosition {
    private final SunPosition sunPosition;

    public LocalSunPosition(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        sunPosition = new SunPosition(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.now(ZoneId.of(zoneId)), longitude, latitude, pressure, temperature);
    }

    public void computePosition() {
        sunPosition.computePosition();
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
}