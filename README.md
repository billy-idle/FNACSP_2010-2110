# Five New Algorithms for the Computation of Sun Position from 2010 to 2110 in Java Language  
**Proposed by [Dr. Roberto Grena.](https://www.researchgate.net/profile/Roberto_Grena)**  
**Implemented by [Guillermo Guzmán Sánchez.](https://plus.google.com/u/0/+GuillermoGuzmánSánchez)**

Heavily based in the [original C++ source code](http://www.solaritaly.enea.it/StrSunPosition/SunPositionEn.php), but applying design patterns and TDD.  

## How-to
Below is an example of computing the sun's position at Rome, using the algorithm with the highest precision:
```java
// (Algorithm #5)
Algorithm algorithm = new AlgorithmFactory().getInstance(Accuracy.HIGHEST); 

// time-zone ID
ZoneId zoneId = ZoneId.of("Europe/Rome");

// Between -> [2010-2110] at UTC
ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Rome")); 

double longitude = 0.21787; // Between -> [0, 2PI] rad
double latitude = 0.73117;  // Between -> [-PI/2, PI/2] rad
double pressure = 1.0;      // Between -> [0.85, 1.069] atm
double temperature = 20.0;  // Between -> [-89.2, 54.0] °C

SunPosition sunPosition = SunPosition.Make(algorithm, zonedDateTime, longitude, latitude, pressure, temperature);
sunPosition.computePosition();
```
Getting the computed values:
```java
String zdt = sunPosition.getZonedDateTime();                // String representation of zonedDateTime
double zenith = sunPosition.getZenith();                    // Zenith angle -> [0,PI] rad
double azimuth = sunPosition.getAzimuth();                  // Azimuth angle -> [-PI,PI] rad
double rightAscension = sunPosition.getRightAscension();    // Right ascension -> [0,2PI] rad
double declination = sunPosition.getDeclination();          // Declination -> [-PI/2, PI/2] rad
double hourAngle = sunPosition.getHourAngle();              // Hour angle -> [-PI,PI] rad
boolean isItDay = sunPosition.isItDay();                    // Return True if the sun is above the horizon
```
Printing the output:
```console
zdt             => 2017-05-09T23:50:45.514+02:00[Europe/Rome]
Zenith          => 2.059485464176653
Azimuth         => 2.7837558034473533
Right Ascension => 0.8202622695721691
Declination     => 0.3069780720546488
Hour Angle      => -3.4719960498660645
Is it day?      => false
```
## Here is another example
In this example the instant sun's position is computed with the time-zone ID passed as String, with the highest precision algorithm (Algorithm #5).  

Using SunPositionNow class as a facade for SunPosition class:
```java
SunPositionNow sunPositionNow = new SunPositionNow("Europe/Rome", longitude, latitude, pressure, temperature);
sunPositionNow.computePosition();
```
The "getting and printing output" process same as before using the sunPositionNow object instead.

