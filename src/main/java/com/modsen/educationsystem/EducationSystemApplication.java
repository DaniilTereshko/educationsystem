package com.modsen.educationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EducationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationSystemApplication.class, args);
	}

}
