package com.dictionary.infrastructure.web.security.jwt;

import com.auth0.jwt.JWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;

    private final String secretKey = "secret";
    private final String issuer = "issuer";
    private final long expiration = 1000;

    @BeforeEach
    void setUp(){
        jwtUtils = new JwtUtils();
        ReflectionTestUtils.setField(jwtUtils, "secretKey", secretKey);
        ReflectionTestUtils.setField(jwtUtils, "issuer", issuer);
        ReflectionTestUtils.setField(jwtUtils, "expirationAccessToken", expiration);
    }

    @Test
    void testUserDatails_thenCreateToken() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtUtils.createToken(userDetails);
        String subject = jwtUtils.getUserToken(token);

        assertEquals("user", subject);

    }

    @Test
    void givenUserDetails_whenCreateToken_thenTokenContainsUsername() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtUtils.createToken(userDetails);
        String username = JWT.decode(token).getSubject();

        assertThat(username).isEqualTo("user");
    }

    @Test
    void givenValidToken_whenVerifyToken_thenTrue() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtUtils.createToken(userDetails);

        boolean isValid = jwtUtils.verifyToken(token);

        assertThat(isValid).isTrue();
    }

    @Test
    void givenInvalidToken_whenVerifyToken_thenFalse() {
        String invalidToken = "invalid.token";

        boolean isValid = jwtUtils.verifyToken(invalidToken);

        assertThat(isValid).isFalse();
    }

    @Test
    void givenToken_whenGetUserToken_thenReturnUsername() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtUtils.createToken(userDetails);
        String username = jwtUtils.getUserToken(token);

        assertThat(username).isEqualTo("user");
    }

}
