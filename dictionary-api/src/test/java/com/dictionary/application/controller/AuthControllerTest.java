package com.dictionary.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dictionary.application.RepositoryMocksConfiguration;
import com.dictionary.application.dto.AuthRequest;
import com.dictionary.application.service.UserSecurityService;
import com.dictionary.common.constant.RouteMapping;
import com.dictionary.infrastructure.persistence.repository.UserRepositoryImpl;
import com.dictionary.infrastructure.web.security.jwt.JwtFilter;
import com.dictionary.infrastructure.web.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({RepositoryMocksConfiguration .class})
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserSecurityService userSecurityService;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserRepositoryImpl userRepository;


    @Test
    void givenRequestEmpty_ShouldReturnBadRequest() throws Exception {
        //Arrange
        AuthRequest authRequest = new AuthRequest("", "");

        //Act & Assert
        mockMvc.perform(post(RouteMapping.AUTH_API_ROOT + RouteMapping.LOGIN_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void giveRequestNull_ShouldReturnBadRequest() throws Exception {
        //Arrange
        AuthRequest authRequest = new AuthRequest(null, null);

        //Act & Assert
        mockMvc.perform(post(RouteMapping.AUTH_API_ROOT + RouteMapping.LOGIN_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testLoginSuccess() throws Exception {
        //Arrange
        AuthRequest authRequest = new AuthRequest("user", "password");
        UserDetails mockUserDetails = mock(UserDetails.class);
        String expectedToken = "token";

        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).willReturn(null);
        given(userSecurityService.loadUserByUsername("user")).willReturn(mockUserDetails);
        given(jwtUtils.createToken(mockUserDetails)).willReturn(expectedToken);

        //Act
        mockMvc.perform(post(RouteMapping.AUTH_API_ROOT + RouteMapping.LOGIN_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());

        // Assert
        verify(authenticationManager).authenticate(any());
        verify(userSecurityService).loadUserByUsername(anyString());
        verify(jwtUtils).createToken(any(UserDetails.class));
    }

    @Test
    void loginFailure_badCredentials() throws Exception {
        // Arrange
        AuthRequest authRequest = new AuthRequest("wrongUser", "password");

        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .willThrow(new BadCredentialsException("Bad credentials"));

        // Act & Assert
        mockMvc.perform(post(RouteMapping.AUTH_API_ROOT + RouteMapping.LOGIN_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authRequest)))
                .andExpect(status().isUnauthorized());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public EntityManagerFactory entityManagerFactory() {
            // Retorna un mock o una implementación stub de EntityManagerFactory
            return Mockito.mock(EntityManagerFactory.class);
        }
    }
}
