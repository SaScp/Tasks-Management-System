package ru.alex.tasksmanagementsystem.configuration;

import jakarta.servlet.FilterChain;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import ru.alex.tasksmanagementsystem.security.Configurer.RequestConfigurer;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.DefaultAccessTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.DefaultRefreshTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.DefaultAccessTokenSerializer;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.DefaultRefreshTokenSerializer;

/**
 * @author Alexander
 * @Class: SecuritySpringConfiguration - class configuration a Tasks Management System
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecuritySpringConfiguration {

    @Qualifier("defaultDaoAuthenticationProvider")
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public RequestConfigurer requestConfigurer() {
        return new RequestConfigurer(new DefaultAccessTokenSerializer(),
                new DefaultRefreshTokenSerializer(),
                new DefaultAccessTokenDeserializer(),
                new DefaultRefreshTokenDeserializer(),
                daoAuthenticationProvider, jdbcTemplate);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RequestConfigurer requestConfigurer) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authManagerReqMatchReg -> authManagerReqMatchReg.anyRequest().permitAll());
        return http.build();
    }


}
