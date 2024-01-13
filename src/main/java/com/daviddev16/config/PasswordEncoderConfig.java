package com.daviddev16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderConfig() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Bean
    PasswordEncoder passwordEnconder() {
        return passwordEncoder;
    }

}
