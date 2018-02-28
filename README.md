# Five New Algorithms for the Computation of Sun Position from 2010 to 2110 in Java Language

**Proposed by [Dr. Roberto Grena.](https://www.researchgate.net/profile/Roberto_Grena)**  
**Implemented in Java languange by [Guillermo Guzmán Sánchez.](https://plus.google.com/u/0/+GuillermoGuzmánSánchez)**

## Overview

This project is based on the [original C++ source code](http://www.solaritaly.enea.it/StrSunPosition/SunPositionEn.php), translated and refactored in Java language applying truly object-oriented design principles, design patterns and test driven development.

## Example using SunPosition

In this example SunPosition computes the position at Rome (Italy), using the highest precision algorithm:

```java
int year = 2020;
int month = 1;
int day = 25;
int hour = 13;
int minute = 30;
int second = 0;
int nanoSecond = 0;
String zoneId = "Europe/Rome";
ZonedDateTime romeZonedDateTime = 
    ZonedDateTime.of(year, month, day, hour, minute, second, nanoSecond, ZoneId.of(zoneId));

double longitude = 0.21787;     // Domain -> [0, 2PI] rad
double latitude = 0.73117;      // Domain -> [-PI/2, PI/2] rad
double pressure = 1.0;          // Domain -> [0.85, 1.069] atm
double temperature = 20.0;      // Domain -> [-89.2, 54.0] °C

String algorithmClassName = "AlgorithmFive"; // Valid names are any Algorithm subclass.

SunPosition sunPosition = 
    new SunPosition(algorithmClassName, romeZonedDateTime, longitude, latitude, pressure, temperature);

sunPosition.compute();
```

*For more information about time zones, see: <https://en.wikipedia.org/wiki/List_of_tz_database_time_zones>*

Getting the computed values:

```java
ZonedDateTime zdt = sunPosition.getZonedDateTime();
double zenith = sunPosition.getZenith();                    // Range -> [0, PI] rad
double azimuth = sunPosition.getAzimuth();                  // Range -> [-PI, PI] rad
double rightAscension = sunPosition.getRightAscension();    // Range -> [0, 2PI] rad
double declination = sunPosition.getDeclination();          // Range -> [-PI/2, PI/2] rad
double hourAngle = sunPosition.getHourAngle();              // Range -> [-PI, PI] rad
boolean isItDaylight = sunPosition.isItDaylight();          // Return True if the sun is above the horizon
```

Printing the output:

```console
zdt             => 2020-01-25T13:30+01:00[Europe/Rome]
Zenith          => 1.097167781330322
Azimuth         => 0.31452718410556935
Right Ascension => 5.364004066519731
Declination     => -0.33191742160701926
Hour Angle      => -5.98761790109209
Is it daylight? => true
```

## Example using InstantSunPosition

In this example InstantSunPosition computes the position using the current date-time from the system clock; the algorithm class name and the time-zone (zoneId) are passed as String:

```java
double longitude = 0.21787;     // Domain -> [0, 2PI] rad
double latitude = 0.73117;      // Domain -> [-PI/2, PI/2] rad
double pressure = 1.0;          // Domain -> [0.85, 1.069] atm
double temperature = 20.0;      // Domain -> [-89.2, 54.0] °C

String algorithmClassName = "AlgorithmFive"; // Valid names are any Algorithm subclass.
String zoneId = "Europe/Rome";

InstantSunPosition instantSunPosition = 
    new InstantSunPosition(algorithmClassName, zoneId, longitude, latitude, pressure, temperature);

instantSunPosition.compute();
```

The "getting and printing output process" same as the previous example using the instantSunPosition object instead.
