package ru.alex.tasksmanagementsystem.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class DeniedFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var agentName = request.getHeader(HttpHeaders.USER_AGENT);
        if (agentName.equals("curl")) {
            response.sendError(HttpStatus.FORBIDDEN.value());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
