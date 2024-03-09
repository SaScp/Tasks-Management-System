package ru.alex.tasksmanagementsystem.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.alex.tasksmanagementsystem.model.Token;
import ru.alex.tasksmanagementsystem.model.Tokens;
import ru.alex.tasksmanagementsystem.security.authentication.UserDetailsWithToken;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.AccessTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.deserializer.RefreshTokenDeserializer;
import ru.alex.tasksmanagementsystem.security.jwt.factory.AccessTokenFactory;
import ru.alex.tasksmanagementsystem.security.jwt.factory.RefreshTokenFactory;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.AccessTokenSerializer;
import ru.alex.tasksmanagementsystem.security.jwt.serializer.RefreshTokenSerializer;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;

@Data
public class RefreshFilter extends OncePerRequestFilter {

    private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/jwt/logout", HttpMethod.POST.name());

    private final SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    private final AccessTokenFactory accessTokenFactory;

    private final RefreshTokenFactory refreshTokenFactory;

    private final AccessTokenSerializer accessTokenSerializer;

    private final RefreshTokenSerializer refreshTokenSerializer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (requestMatcher.matches(request)) {
            if (securityContextRepository.containsContext(request)) {
                final var context = securityContextRepository.loadDeferredContext(request).get();
                if (context.getAuthentication() instanceof PreAuthenticatedAuthenticationToken &&
                        context.getAuthentication().getPrincipal() instanceof UserDetailsWithToken token &&
                        context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("REFRESH_REFRESH"))) {

                    Token refreshToken = refreshTokenFactory.apply(context.getAuthentication());
                    Token accessToken = accessTokenFactory.apply(refreshToken);

                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    this.objectMapper.writeValue(response.getWriter(), new Tokens(accessTokenSerializer.apply(accessToken),
                            Date.from(accessToken.expireAt()), refreshTokenSerializer.apply(refreshToken), Date.from(refreshToken.expireAt())));

                    response.setStatus(HttpStatus.NO_CONTENT.value());
                    return;
                }
            }
            throw new AccessDeniedException("Access denied");
        }

        filterChain.doFilter(request, response);
    }
}

