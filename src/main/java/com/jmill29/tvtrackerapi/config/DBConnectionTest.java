package com.jmill29.tvtrackerapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DBConnectionTest {

    @Bean
    public CommandLineRunner testDBConnection(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                if (conn != null) {
                    System.out.println("Database connection successful!");
                } else {
                    System.out.println("Failed to make connection!");
                }
            } catch (Exception e) {
                System.err.println("Error connecting to the database: " + e.getMessage());
            }
        };
    }

}
