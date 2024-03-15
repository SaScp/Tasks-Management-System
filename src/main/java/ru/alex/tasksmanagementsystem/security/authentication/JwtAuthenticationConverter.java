package ru.alex.tasksmanagementsystem.security.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import ru.alex.tasksmanagementsystem.model.response.Token;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.AccessTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.RefreshTokenDeserializer;



@AllArgsConstructor
public class JwtAuthenticationConverter implements AuthenticationConverter {

    private final AccessTokenDeserializer accessTokenDeserializer;

    private final RefreshTokenDeserializer refreshTokenDeserializer;

    @Override
    public Authentication convert(HttpServletRequest request) {

        String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            String serializerToken = authorizationToken.substring(7);
            Token token = accessTokenDeserializer.apply(serializerToken);
            if (token != null) {
                return new PreAuthenticatedAuthenticationToken(serializerToken, token);
            }
            token = refreshTokenDeserializer.apply(serializerToken);
            if (token != null) {
                return new PreAuthenticatedAuthenticationToken(serializerToken, token);
            }
        }

        return null;
    }
}
