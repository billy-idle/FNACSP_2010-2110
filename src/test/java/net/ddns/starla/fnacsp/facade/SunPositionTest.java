package net.ddns.starla.fnacsp.facade;

import net.ddns.starla.fnacsp.template.entities.Pressure;
import net.ddns.starla.fnacsp.template.entities.Temperature;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;

import static net.ddns.starla.fnacsp.template.entities.Latitude.MAX_LATITUDE_RAD;
import static net.ddns.starla.fnacsp.template.entities.Latitude.MIN_LATITUDE_RAD;
import static net.ddns.starla.fnacsp.template.entities.Longitude.MAX_LONGITUDE_RAD;
import static net.ddns.starla.fnacsp.template.entities.Longitude.MIN_LONGITUDE_RAD;
import static net.ddns.starla.fnacsp.template.entities.Time.BEGINNING_TIME_INTERVAL;
import static net.ddns.starla.fnacsp.template.entities.Time.END_TIME_INTERVAL;
import static org.junit.Assert.assertEquals;


public class SunPositionTest {

    private final static String algorithmClassName = "AlgorithmFive";
    private final double longitude = 0.21787;
    private final double latitude = 0.73117;
    private final double pressure = 1.0;
    private final double temperature = 20.0;
    private final ZonedDateTime europeRomeBTI = BEGINNING_TIME_INTERVAL.withZoneSameInstant(ZoneId.of("Europe/Rome"));
    private final ZonedDateTime europeRomeETI = END_TIME_INTERVAL.withZoneSameInstant(ZoneId.of("Europe/Rome"));
    private SunPosition sp;

    @Test(expected = RuntimeException.class)
    public void whenBeforeBeginningTimeInterval_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI.minusNanos(1), longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenAfterEndTimeInterval_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeETI.plusNanos(1), longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenLongitudeBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, MIN_LONGITUDE_RAD - .1, latitude,
                pressure, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenLongitudeBeyondItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, MAX_LONGITUDE_RAD + .1, latitude,
                pressure, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenLatitudeBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, longitude, MIN_LATITUDE_RAD - .1,
                pressure, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenLatitudeBeyondItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, longitude, MAX_LATITUDE_RAD + .1,
                pressure, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenPressureBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, longitude, latitude,
                Pressure.MIN_PRESS_IN_ATM - .1, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenPressureAboveItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, longitude, latitude,
                Pressure.MAX_PRESS_IN_ATM + .1, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenTemperatureBelowItsMin_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, longitude, latitude, pressure,
                Temperature.MIN_TEMP_CELSIUS - .1);
    }

    @Test(expected = RuntimeException.class)
    public void whenTemperatureAboveItsMax_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName, europeRomeBTI, longitude, latitude, pressure,
                Temperature.MAX_TEMP_CELSIUS + .1);
    }

    @Test(expected = RuntimeException.class)
    public void whenAlgorithmClassNameNotFound_ShouldThrowIllegalArgumentException() {
        new SunPosition(algorithmClassName.concat("2498#$@%^&("), europeRomeBTI, longitude, latitude,
                pressure, temperature);
    }

    @Test(expected = RuntimeException.class)
    public void whenClassNameFoundButNotAlgorithmType_ShouldThrowIllegalArgumentException() {
        new SunPosition("AlgorithmDuTy", europeRomeBTI, longitude, latitude, pressure,
                temperature);
    }

    @Test(expected = ZoneRulesException.class)
    public void whenZoneIdUnknown_ShouldThrowZoneRulesException() {
        new SunPosition("Europe/Bogota", 0.21787, 0.73117, 1.0, 20.0).compute();
    }

    @Test
    public void printingOutput() {
        sp = new SunPosition(algorithmClassName,
                ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Europe/Rome")), longitude, latitude, pressure,
                temperature);

        sp.compute();

        String expectedString = output();

        assertEquals(expectedString, sp.toString());
    }

    private String output() {
        StringBuilder table = new StringBuilder();
        StringBuilder row = new StringBuilder();
        StringBuilder output = new StringBuilder();

        String desc = String.format("Sun Ephemeris for %1$s at Long. %3$f and Lat. %4$f on %2$tB %2$te of %2$tY%n%n",
                sp.getZonedDateTime().getZone(), sp.getZonedDateTime(), sp.getLongitude(), sp.getLatitude());

        String tableHeader = String.format("%-10s %-10s %-10s %-13s %-15s %-10s %8s %8s %8s%n", "Time", "Zenith",
                "Azimuth", "Right Asc.", "Declination", "Hour Angle", "Pres.", "Temp.", "ToD");

        row.append(String.format("%tT", sp.getZonedDateTime()));
        row.append(String.format("%9.3f", sp.getZenith()));
        row.append(String.format("%12.3f", sp.getAzimuth()));
        row.append(String.format("%12.3f", sp.getRightAscension()));
        row.append(String.format("%15.3f", sp.getDeclination()));
        row.append(String.format("%15.3f", sp.getHourAngle()));
        row.append(String.format("%10.1f", sp.getPressure()));
        row.append(String.format("%10.1f", sp.getTemperature()));
        row.append(String.format("%10s%n", sp.getTimeOfDay()));

        table.append(tableHeader).append(row);

        String footer = String.format("%n%s%n%s%n%s%n%s%n", "* angles in radians (rad)",
                "* Pressure in atmospheres (atm)", "* Temperature in Celsius degrees (°C)",
                "* ToD stands for \"Time of Day\"");

        output.append(desc).append(table).append(footer);

        return output.toString();
    }

    private double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}