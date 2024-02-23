package com.dictionary.application.controller;


import com.dictionary.application.dto.AuthRequest;
import com.dictionary.application.dto.AuthResponse;
import com.dictionary.application.service.UserSecurityService;
import com.dictionary.common.constant.RouteMapping;
import com.dictionary.infrastructure.web.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ch.qos.logback.core.util.OptionHelper.isNullOrEmpty;

@RestController
@RequestMapping(RouteMapping.AUTH_API_ROOT)
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserSecurityService userSecurityService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserSecurityService userSecurityService) {
        this.authenticationManager = authenticationManager;
        this.userSecurityService = userSecurityService;
    }

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = RouteMapping.LOGIN_API, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            if (isNullOrEmpty(authRequest.getUsername())  || isNullOrEmpty(authRequest.getPassword())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername().trim(), authRequest.getPassword().trim()));
            UserDetails userDetails = userSecurityService.loadUserByUsername(authRequest.getUsername());
            String jwt = jwtUtils.createToken(userDetails);

            return new ResponseEntity<>(new AuthResponse(jwt), HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
