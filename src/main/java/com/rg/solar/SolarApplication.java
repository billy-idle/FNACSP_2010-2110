package com.rg.solar;

import com.rg.solar.configuration.Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolarApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(Util util) {
		return args -> {
			System.out.println(util);
		};
	}
}
