package org.example.restrsiprojekt.Controller.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    Logger log = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        log.info("Żądanie: " + request.getMethod() + " " + request.getRequestURI());
        filterChain.doFilter(request, response);
        log.info("Odpowiedź: " + response.getStatus());
    }
}
