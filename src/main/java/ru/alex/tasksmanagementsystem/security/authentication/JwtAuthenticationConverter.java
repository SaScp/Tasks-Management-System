package ru.alex.tasksmanagementsystem.security.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.AccessTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.RefreshTokenDeserializer;



@AllArgsConstructor
public class JwtAuthenticationConverter implements AuthenticationConverter {

    private final AccessTokenDeserializer accessTokenDeserializer;

    private final RefreshTokenDeserializer refreshTokenDeserializer;

    @Override
    public Authentication convert(HttpServletRequest request) {

        String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationToken.startsWith("Bearer ")) {
            if (true) {

            }
            if (true) {

            }
        }

        return null;
    }
}
