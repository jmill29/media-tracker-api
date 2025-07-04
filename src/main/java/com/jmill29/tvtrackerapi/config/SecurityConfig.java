package com.jmill29.tvtrackerapi.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Configuration class for Spring Security settings.
 * <p>
 * Sets up authentication using a JDBC data source, password encoding, and HTTP security rules.
 * This configuration uses HTTP Basic authentication and JDBC-based user details management.
 * </p>
 */
@Configuration
public class SecurityConfig {


    /**
     * Configures the {@link UserDetailsService} to use a JDBC data source for authentication.
     *
     * @param dataSource the {@link DataSource} connected to the user database
     * @return a {@link UserDetailsService} backed by JDBC
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


    /**
     * Provides a password encoder bean using BCrypt hashing algorithm.
     *
     * @return a {@link PasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain for HTTP requests.
     * <p>
     * All requests require authentication. CSRF protection is disabled and HTTP Basic authentication is enabled.
     * </p>
     *
     * @param http the {@link HttpSecurity} to modify
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()) // ✅ Modern lambda-based CSRF config
            .httpBasic(Customizer.withDefaults()); // Enable Basic Auth

        return http.build();
    }
}
