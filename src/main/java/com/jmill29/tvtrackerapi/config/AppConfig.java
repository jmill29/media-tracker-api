package com.jmill29.tvtrackerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for application-wide Spring Beans.
 * <p>
 * This class defines and provides core beans that are shared across
 * the application, such as security encoders.
 * </p>
 */
@Configuration
public class AppConfig {

    /**
     * Provides a {@link PasswordEncoder} bean that uses BCrypt hashing.
     * <p>
     * This bean is used for securely encoding user passwords before storing
     * them in the database and for verifying login credentials.
     * </p>
     *
     * @return a BCrypt-based password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
