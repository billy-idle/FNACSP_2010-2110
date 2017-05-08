package net.ddns.starla.fnacsp.pattern.facade;


import net.ddns.starla.fnacsp.pattern.factory.Accuracy;
import net.ddns.starla.fnacsp.pattern.factory.AlgorithmFactory;
import net.ddns.starla.fnacsp.pattern.strategy.SunPosition;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SunPositionNow {
    private final SunPosition sunPosition;

    public SunPositionNow(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        sunPosition = SunPosition.Make(new AlgorithmFactory().getInstance(Accuracy.HIGHEST),
                ZonedDateTime.now(ZoneId.of(zoneId)), longitude, latitude, pressure, temperature);

        sunPosition.computePosition();
    }

    public double getZenith() {
        return sunPosition.getZenith();
    }

    public double getAzimuth() {
        return sunPosition.getAzimuth();
    }

    public String getZonedDateTime() {
        return sunPosition.getZonedDateTime();
    }

    public boolean isItDay() {
        return sunPosition.isItDay();
    }
}