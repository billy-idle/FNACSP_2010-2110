# Five New Algorithms for the Computation of Sun Position from 2010 to 2110 in Java Language

**Proposed by [Dr. Roberto Grena.](https://www.researchgate.net/profile/Roberto_Grena)**  
**Implemented in Java languange by [Guillermo Guzmán Sánchez.](https://plus.google.com/u/0/+MMcFly)**

[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=net.ddns.starla%3A5NACSP&metric=alert_status)](https://sonarcloud.io/dashboard/index/net.ddns.starla%3A5NACSP&metric=alert_status)

## Overview

This project is based on the [original C++ source code](http://www.solaritaly.enea.it/StrSunPosition/SunPositionEn.php), translated and refactored in Java language simplifying the extensibility and portability via a pattern-oriented OO design, SOLID principles and test-driven development allowing the addition of new algorithms (e.g., _"Algorithm #6"_) without modifying the structure and implementation.

## How to use it

In order to compute the sun position, you have to create an instance of SunPosition; which it has two constructors:

1. The first one is meant to compute what I call **"instant sun position"** using the highest precision algorithm (Algorithm #5) taking the current date and time from the system clock; the time-zone (zoneId) is passed as String:

```java
public SunPosition(String zoneId, double longitude, double latitude, double pressure, double temperature)
```

2. In the second one, you have more control because you can choose the algorithm; zoneId (String) it's replaced with an ZonedDateTime object:

```java
public SunPosition(String algorithmClassName, ZonedDateTime zonedDateTime, double longitude, double latitude, 
                        double pressure, double temperature)
```

*For more information about time zones, see: <https://en.wikipedia.org/wiki/List_of_tz_database_time_zones>*

## Example 1

In this example SunPosition computes the position using the current date and time from the system clock; the time-zone (zoneId) is passed as String:

```java
double longitude = 0.21787;     // Domain -> [0, 2PI] rad
double latitude = 0.73117;      // Domain -> [-PI/2, PI/2] rad
double pressure = 1.0;          // Domain -> [0.85, 1.069] atm
double temperature = 20.0;      // Domain -> [-89.2, 54.0] °C
String zoneId = "Europe/Rome";

SunPosition sunPosition = new SunPosition(zoneId, longitude, latitude, pressure, temperature);
sunPosition.compute();
```

## Example 2

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
