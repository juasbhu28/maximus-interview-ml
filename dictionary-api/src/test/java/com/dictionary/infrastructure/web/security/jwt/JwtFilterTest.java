package com.dictionary.infrastructure.web.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dictionary.application.service.UserSecurityService;
import com.dictionary.common.constant.RouteMapping;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtFilterTest {

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserSecurityService userSecurityService;

    @InjectMocks
    private JwtFilter jwtFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private final String PUBLIC_PATH = RouteMapping.PUBLIC_API;
    private final String PRIVATE_PATH = RouteMapping.PRIVATE_API;
    private final String AUTH_HEADER = "X-AUTH-USER";

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void givenWitoutHeaderAndPublicPath_thenShouldNotAuthenticate() throws Exception {
        request.setRequestURI(PUBLIC_PATH + "/test");

        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void givenHeaderIsNull_thenResponseIsUnauthorized() throws Exception {
        request.addHeader(AUTH_HEADER, "");
        request.setRequestURI(PRIVATE_PATH + "test");

        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }


    @Test
    void whenJwtIsInvalid_thenResponseIsUnauthorized() throws Exception {
        request.addHeader(AUTH_HEADER, "invalidToken");
        request.setRequestURI(PRIVATE_PATH + "/test");

        doThrow(new JWTVerificationException("Invalid token"))
                .when(jwtUtils).verifyToken(anyString());

        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_UNAUTHORIZED);
        assertThat(response.getContentAsString()).isEqualTo("Unauthorized");
    }

    @Test
    void whenJwtIsValid_thenShouldAuthenticate() throws Exception {
        String jwtToken = "validToken";
        request.addHeader(AUTH_HEADER, jwtToken);
        request.setRequestURI("/dictionary-api/v1/private/test");

        when(jwtUtils.verifyToken(jwtToken)).thenReturn(true);
        when(jwtUtils.getUserToken(jwtToken)).thenReturn("user");
        UserDetails userDetails = new User("user", "password", new ArrayList<>());
        when(userSecurityService.loadUserByUsername("user")).thenReturn(userDetails);

        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
        assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("user");
    }

}
