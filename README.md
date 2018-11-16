# Five New Algorithms for the Computation of Sun Position from 2010 to 2110 in Java Language

Proposed by [Dr. Roberto Grena.](https://www.researchgate.net/profile/Roberto_Grena)  
Implemented in Java languange by [Guillermo Guzmán Sánchez.](mailto:guillesupremacy@gmail.com)

[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=guillesup_FNACSP_2010-2110&metric=alert_status)](https://sonarcloud.io/dashboard?id=guillesup_FNACSP_2010-2110)

https://sonarcloud.io/dashboard?id=guillesup_FNACSP_2010-2110

## Overview

This project is based on the [original C++ source code](http://www.solaritaly.enea.it/StrSunPosition/SunPositionEn.php), translated and refactored in Java language simplifying the extensibility and portability via a pattern-oriented OO design along with the SOLID principles and test-driven development allowing the addition of new algorithms (e.g., `AlgorithmSix`).

## How to use it

There are two ways to compute the sun position:

1. The first one uses the highest precision algorithm: `AlgorithmFive`, getting the current date-time from the system's clock; the time-zone or `zoneId` is passed as String:

```java
double longitude = 0.21787;     // Domain -> [0, 2PI] rad
double latitude = 0.73117;      // Domain -> [-PI/2, PI/2] rad
double pressure = 1.0;          // Domain -> [0.85, 1.069] atm
double temperature = 20.0;      // Domain -> [-89.2, 54.0] °C
String zoneId = "Europe/Rome";

SunPosition sp = new SunPosition(zoneId, longitude, latitude, pressure, temperature);

sp.compute();
```

2. In the second one, you have to pass the algorithm's name as `String` (`AlgorithmOne`, `AlgorithmTwo`, `AlgorithmThree`, `AlgorithmFour`, or, `AlgorithmFive`) along with the specific date-time needed, therefore `zoneId` is replaced with a `ZonedDateTime` object instead:

```java
int year = 2020;
int month = 1;
int day = 25;
int hour = 13;
int minute = 30;
int second = 0;
int nanoSecond = 0;
String zoneId = "Europe/Rome";

ZonedDateTime romeZDT =
    ZonedDateTime.of(year, month, day, hour, minute, second, nanoSecond, ZoneId.of(zoneId));

double longitude = 0.21787;     // Domain -> [0, 2PI] rad
double latitude = 0.73117;      // Domain -> [-PI/2, PI/2] rad
double pressure = 1.0;          // Domain -> [0.85, 1.069] atm
double temperature = 20.0;      // Domain -> [-89.2, 54.0] °C

String algorithm = "AlgorithmFive"; // "AlgorithmOne", "AlgorithmTwo", "AlgorithmThree", "AlgorithmFour", or, "AlgorithmFive".

SunPosition sp =
    new SunPosition(algorithm, romeZDT, longitude, latitude, pressure, temperature);

sp.compute();
```

*For more information about time zones, see: <https://en.wikipedia.org/wiki/List_of_tz_database_time_zones>*

## Printing the output

```java
System.out.println(sp);
```

```console
DateTime    2020-01-25T13:30+01:00[Europe/Rome]
Zenith      1.1 rad     // Range -> [0, PI] rad
Azimuth     0.31 rad    // Range -> [-PI, PI] rad
Right Asc.  5.36 rad    // Range -> [0, 2PI] rad
Declination -0.33 rad   // Range -> [-PI/2, PI/2] rad
Hour Angle  -5.99 rad   // Range -> [-PI, PI] rad
ToD         Daytime     // ToD (Time of Day) either Daytime or Night.
```
