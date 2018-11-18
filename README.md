# Five New Algorithms for the Computation of Sun Position from 2010 to 2110 in Java Language

Proposed by [Dr. Roberto Grena.](https://www.researchgate.net/profile/Roberto_Grena)  
Implemented in Java languange by [Guillermo Guzmán Sánchez.](mailto:guillesupremacy@gmail.com)

[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=guillesup_FNACSP_2010-2110&metric=alert_status)](https://sonarcloud.io/dashboard?id=guillesup_FNACSP_2010-2110)

## Overview

This project is based on the [original C++ source code](http://www.solaritaly.enea.it/StrSunPosition/SunPositionEn.php), translated and refactored in Java language simplifying the extensibility and portability via a pattern-oriented OO design along with the SOLID principles and test-driven development allowing the addition of new algorithms (e.g., `AlgorithmSix`).

## Sun Position Class Diagram

![alt text][fnacsp-class-diagram]

## How to use `SunPosition`

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

ZonedDateTime romeZDT = ZonedDateTime.of(year, month, day, hour, minute, second, nanoSecond, ZoneId.of(zoneId));

double longitude = 0.21787;     // Domain -> [0, 2PI] rad
double latitude = 0.73117;      // Domain -> [-PI/2, PI/2] rad
double pressure = 1.0;          // Domain -> [0.85, 1.069] atm
double temperature = 20.0;      // Domain -> [-89.2, 54.0] °C

String algorithm = "AlgorithmFive"; // "AlgorithmOne", "AlgorithmTwo", "AlgorithmThree", "AlgorithmFour", or, "AlgorithmFive".

SunPosition sp = new SunPosition(algorithm, romeZDT, longitude, latitude, pressure, temperature);

sp.compute();
```

*For more information about time zones, see: <https://en.wikipedia.org/wiki/List_of_tz_database_time_zones>*

## Printing the sun position

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

## Sun Ephemeris

`SunEphemeris` (as the name implies) allows you to compute the sun ephemeris for one day starting at 00:00:00 hours evenly spaced in minutes. In order to compute the ephemeris you have to pass the `zoneId` along with the `longitude`, `latitude`, `pressure` and `temperature`. `SunEphemeris` gets the current date from the system clock and use the `AlgorithmFive`.

## Sun Ephemeris Class Diagram

![alt text][se-class-diagram]

## How to use `SunEphemeris`

```java
double temperature = 20.0;
double pressure = 1.0;
double latitude = 0.73117;
double longitude = 0.21787;

SunEphemeris se = new SunEphemeris("Europe/Rome", longitude, latitude, pressure, temperature);
se.compute();
```

## Printing the ephemeris

```java
System.out.println(se);
```

```console
Sun Ephemeris for Europe/Rome on November 18 of 2018

Time       Zenith     Azimuth    Right Asc.    Declination     Hour Angle      ToD
00:00:00     2.744    -3.089          4.070         -0.334         -9.403     Night
00:01:00     2.744    -3.079          4.070         -0.334         -9.399     Night
00:02:00     2.744    -3.068          4.070         -0.334         -9.395     Night
...
07:09:00     1.560    -1.110          4.076         -0.335         -7.532   Daytime
07:10:00     1.558    -1.107          4.076         -0.335         -7.527   Daytime
07:11:00     1.555    -1.104          4.076         -0.335         -7.523   Daytime
...
23:59:00     2.748    -3.102          4.088         -0.338         -9.409     Night

* angles in radians (rad)

```

[fnacsp-class-diagram]: images/fnacsp-class-diagram.png "Sun Position Class Diagram"
[se-class-diagram]: images/se-class-diagram.png "Sun Ephemeris Class Diagram"