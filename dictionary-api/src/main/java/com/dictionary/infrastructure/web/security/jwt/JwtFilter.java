package com.dictionary.infrastructure.web.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dictionary.application.service.UserSecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private static final String X_AUTH_USER = "X-AUTH-USER";

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserSecurityService userSecurityService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {

            if (pathAllow(request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwtHeader = request.getHeader(X_AUTH_USER);
            if (jwtHeader == null || jwtHeader.isEmpty()) {
                throw new JWTVerificationException("Invalid Authorization header");
            }

            if (!this.jwtUtils.verifyToken(jwtHeader)) {
                throw new JWTVerificationException("Invalid token");
            }

            String username = this.jwtUtils.getUserToken(jwtHeader);
            User user = (User) this.userSecurityService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), user.getPassword(), user.getAuthorities());

            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            log.error("Error: ", e);
        }

        filterChain.doFilter(request, response);

    }

    private boolean pathAllow(String path) {
        //Evaluate if the path contains /dictionary-api/vi/public/
        return path.contains("/dictionary-api/v1/public/");
    }
}