package com.awernercs.surveyor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Surveyor is a SpringBoot application designed with the MVC design pattern following
 * my completion of Launch Code 101 in the Summer of 2017.
 *
 * While employed at Bullhorn, my supervisor began bringing some fun to our team meetings
 * using "fun polls." I wanted a way to automatically generate poll questions and manage the polls,
 * so I created Surveyor.
 *
 * Surveyor is a work in progress as of 7/10/2017. 
 *
 */

@SpringBootApplication
public class SurveyorApplication {
	public static void main(String[] args) {
		SpringApplication.run(SurveyorApplication.class, args);
	}
}
