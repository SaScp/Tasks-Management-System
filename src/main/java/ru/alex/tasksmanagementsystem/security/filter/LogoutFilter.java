package ru.alex.tasksmanagementsystem.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.alex.tasksmanagementsystem.security.authentication.UserDetailsWithToken;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;

public class LogoutFilter extends OncePerRequestFilter {

    private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/jwt/logout",HttpMethod.POST.name());

    private final SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    private final JdbcTemplate jdbcTemplate;

    public LogoutFilter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (requestMatcher.matches(request)) {
            if (securityContextRepository.containsContext(request)) {
                final var context = securityContextRepository.loadDeferredContext(request).get();
                if (context.getAuthentication() instanceof PreAuthenticatedAuthenticationToken &&
                        context.getAuthentication().getPrincipal() instanceof UserDetailsWithToken token &&
                        context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("REFRESH_LOGOUT"))) {
                    this.jdbcTemplate.update("insert into t_logout value(?, ?)",token.getToken().UUID(), Date.from(token.getToken().expireAt()));

                    response.setStatus(HttpStatus.NO_CONTENT.value());

                    return;
                }
            }
            throw new AccessDeniedException("Access denied");
        }
        filterChain.doFilter(request, response);
    }
}
