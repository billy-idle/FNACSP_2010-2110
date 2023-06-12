package com.rg.solar.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "util")
public class Util {
  private double pi;
  private double pim;
  private double maxLatitudeRad;
  private double minLatitudeRad;
  private double maxLongitudeRad;
  private double minLongitudeRad;
  private double minPressureInAtm;
  private double maxPressureInAtm;
  private double minTempCelsius;
  private double maxTempCelsius;
  private Date startTimeInterval;
  private Date endTimeInterval;
}
