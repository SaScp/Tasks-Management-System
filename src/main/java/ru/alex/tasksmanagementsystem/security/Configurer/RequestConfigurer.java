package ru.alex.tasksmanagementsystem.security.Configurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import ru.alex.tasksmanagementsystem.security.authentication.JwtAuthenticationConverter;
import ru.alex.tasksmanagementsystem.security.authentication.TokensAuthenticationUserDetailsService;
import ru.alex.tasksmanagementsystem.security.filter.DeniedFilter;
import ru.alex.tasksmanagementsystem.security.filter.LogoutFilter;
import ru.alex.tasksmanagementsystem.security.filter.RefreshFilter;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.AccessTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.RefreshTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.factory.AccessJwtFactory;
import ru.alex.tasksmanagementsystem.security.jwt.factory.RefreshJwtFactory;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.AccessTokenSerializer;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.RefreshTokenSerializer;

/**
 * @author Alexander
 * @Class RequestConfigurer - configure class - class for setting AuthenticationFilter and more filter with providers
 */

@Builder
@AllArgsConstructor
public class RequestConfigurer extends AbstractHttpConfigurer<RequestConfigurer, HttpSecurity> {

    private final AccessTokenSerializer accessTokenSerializer;

    private final RefreshTokenSerializer refreshTokenSerializer;

    private final AccessTokenDeserializer accessTokenDeserializer;

    private final RefreshTokenDeserializer refreshTokenDeserializer;

    private final AuthenticationProvider defaultDaoAuthenticationProvider;

    private final JdbcTemplate jdbcTemplate;


    @Override
    public void init(HttpSecurity builder) throws Exception {
        super.init(builder);
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(new TokensAuthenticationUserDetailsService(this.jdbcTemplate));
        LogoutFilter logoutFilter = new LogoutFilter(jdbcTemplate);
        RefreshFilter refreshFilter = new RefreshFilter(new AccessJwtFactory(),
                new RefreshJwtFactory(), accessTokenSerializer, refreshTokenSerializer);


        builder.addFilterBefore(getAuthenticationFilter(authenticationManager), CsrfFilter.class)
                .addFilterBefore(new DeniedFilter(), DisableEncodeUrlFilter.class)
                .addFilterBefore(refreshFilter, ExceptionTranslationFilter.class)
                .addFilterAfter(logoutFilter, ExceptionTranslationFilter.class)
                .authenticationProvider(preAuthenticatedAuthenticationProvider)
                .authenticationProvider(this.defaultDaoAuthenticationProvider);
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager,
                new JwtAuthenticationConverter(accessTokenDeserializer, refreshTokenDeserializer));
        authenticationFilter.setFailureHandler(((request, response, exception) -> response.sendError(HttpStatus.FORBIDDEN.value())));
        return authenticationFilter;
    }
}
