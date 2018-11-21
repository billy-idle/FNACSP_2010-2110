# Five New Algorithms for the Computation of Sun Position from 2010 to 2110 in Java Language

Proposed by [Dr. Roberto Grena.](https://www.researchgate.net/profile/Roberto_Grena)  
Implemented in Java language by [Guillermo Guzmán Sánchez.](mailto:guillesupremacy@gmail.com) 

[![CircleCI](https://circleci.com/gh/guillesup/FNACSP_2010-2110.svg?style=svg)](https://circleci.com/gh/guillesup/FNACSP_2010-2110)  
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=guillesup_FNACSP_2010-2110&metric=alert_status)](https://sonarcloud.io/dashboard?id=guillesup_FNACSP_2010-2110)

## Overview

This project is based on the [original C++ source code](http://www.solaritaly.enea.it/StrSunPosition/SunPositionEn.php), translated and refactored in Java language simplifying the extensibility and portability via a pattern-oriented OO design along with the SOLID principles and test-driven development allowing the addition of new algorithms (e.g., `AlgorithmSix`).

## Sun Position Class Diagram

![alt text][fnacsp-class-diagram]

## How to compute the sun position

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
Sun Ephemeris for Europe/Rome at Long. 0.217870 and Lat. 0.731170 on January 25 of 2020

Time       Zenith     Azimuth    Right Asc.    Declination     Hour Angle    Pres.    Temp.      ToD
13:30:00    1.097       0.315       5.364         -0.332         -5.988       1.0      20.0   Daytime

* angles in radians (rad)
* Pressure in atmospheres (atm)
* Temperature in Celsius degrees (°C)
* ToD stands for "Time of Day"
```

## Sun Ephemeris

`SunEphemeris` (as the name implies) allows you to compute the sun ephemeris for one day starting at 00:00:00 hours evenly spaced in minutes. In order to compute the ephemeris you have to pass the `zoneId` along with the `longitude`, `latitude`, `pressure` and `temperature`. `SunEphemeris` gets the current date from the system's clock and uses the `AlgorithmFive`.

## Sun Ephemeris Class Diagram

![alt text][se-class-diagram]

## How to compute the sun ephemeris

```java
double temperature = 20.0;
double pressure = 1.0;
double latitude = 0.73117;
double longitude = 0.21787;

SunEphemeris se = new SunEphemeris("Europe/Rome", longitude, latitude, pressure, temperature);
se.compute();
```

## Printing the sun ephemeris

```java
System.out.println(se);
```

```console
Sun Ephemeris for Europe/Rome at Long. 0.217870 and Lat. 0.731170 on November 19 of 2018

Time       Zenith     Azimuth    Right Asc.    Declination     Hour Angle    Pres.    Temp.      ToD
00:00:00    2.748      -3.091       4.088         -0.338         -9.404       1.0      20.0     Night
00:01:00    2.748      -3.081       4.088         -0.338         -9.400       1.0      20.0     Night
00:02:00    2.748      -3.070       4.088         -0.338         -9.396       1.0      20.0     Night
...
07:10:00    1.561      -1.105       4.094         -0.339         -7.528       1.0      20.0   Daytime
07:11:00    1.558      -1.102       4.094         -0.339         -7.524       1.0      20.0   Daytime
07:12:00    1.556      -1.099       4.094         -0.339         -7.520       1.0      20.0   Daytime
...
23:59:00    2.753      -3.104       4.107         -0.342         -9.410       1.0      20.0     Night

* angles in radians (rad)
* Pressure in atmospheres (atm)
* Temperature in Celsius degrees (°C)
* ToD stands for "Time of Day"
```

[fnacsp-class-diagram]: images/fnacsp-class-diagram.png "Sun Position Class Diagram"
[se-class-diagram]: images/se-class-diagram.png "Sun Ephemeris Class Diagram"
