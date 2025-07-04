
/**
 * Main entry point for the TV Tracker API Spring Boot application.
 * <p>
 * This class bootstraps the application using Spring Boot's auto-configuration and component scanning.
 * Run this class to start the embedded server and expose the REST API endpoints.
 */
package com.jmill29.tvtrackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TvtrackerapiApplication {

	/**
	 * Starts the TV Tracker API Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(TvtrackerapiApplication.class, args);
	}

}
