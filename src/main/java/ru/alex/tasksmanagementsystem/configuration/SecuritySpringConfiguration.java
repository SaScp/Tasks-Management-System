package ru.alex.tasksmanagementsystem.configuration;

import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Alexander
 * @Class: SecuritySpringConfiguration - class configuration a Tasks Management System
 */
@Configuration
@EnableWebSecurity
public class SecuritySpringConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authManagerReqMatchReg -> authManagerReqMatchReg.anyRequest().permitAll()).build();
    }


}
