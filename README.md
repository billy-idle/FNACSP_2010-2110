# Five New Algorithms for the Computation of Sun Position from 2010 to 2110 in Java Language  
**Proposed by [Dr. Roberto Grena.](https://www.researchgate.net/profile/Roberto_Grena)**  
**Implemented by [Guillermo Guzmán Sánchez.](https://plus.google.com/u/0/+GuillermoGuzmánSánchez)**

Based on the [original C++ source code](http://www.solaritaly.enea.it/StrSunPosition/SunPositionEn.php), applying design patterns and TDD.  

## How to use it
Below is an example of computing the sun-position at Rome, using the lowest precision algorithm: 
```java
int year = 2020;  
int month = 1;
int day = 25;
int hour = 13;
int minute = 30;
int second = 0;
int nanoSecond = 0;
String zoneId = "Europe/Rome";

ZonedDateTime romeZonedDateTime;
romeZonedDateTime = ZonedDateTime.of(year, month, day, hour, minute, second, nanoSecond, ZoneId.of(zoneId));

double longitude = 0.21787;     // Domain -> [0, 2PI] rad
double latitude = 0.73117;      // Domain -> [-PI/2, PI/2] rad
double pressure = 1.0;          // Domain -> [0.85, 1.069] atm
double temperature = 20.0;      // Domain -> [-89.2, 54.0] °C

String algorithmClassName = "AlgorithmOne"; // Valid values are any Algorithm subclass.

SunPosition sunPosition = SunPosition.of(algorithmClassName, zonedDateTime, longitude, latitude, pressure, temperature);
sunPosition.computePosition();
```
*If you don't know your timezone value, you can look it up (see: https://en.wikipedia.org/wiki/List_of_tz_database_time_zones)*

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
## Here is another example
In this example the instant sun-position is computed with the time-zone ID and the algorithm class name both passed as String.  

Using SunPositionNow class as a facade for SunPosition class:
```java
SunPositionNow sunPositionNow = new SunPositionNow("AlgorithmTwo", "Europe/Rome", longitude, latitude, pressure, temperature);
sunPositionNow.computePosition();
```
The "getting and printing output" process same as before using the sunPositionNow object instead.

