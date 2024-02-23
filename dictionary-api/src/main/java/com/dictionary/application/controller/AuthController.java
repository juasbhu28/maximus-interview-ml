package com.dictionary.application.controller;


import com.dictionary.application.dto.AuthRequestDto;
import com.dictionary.application.dto.AuthResponseDto;
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
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        try {
            if (isNullOrEmpty(authRequestDto.getUsername())  || isNullOrEmpty(authRequestDto.getPassword())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequestDto.getUsername().trim(), authRequestDto.getPassword().trim()));
            UserDetails userDetails = userSecurityService.loadUserByUsername(authRequestDto.getUsername());
            String jwt = jwtUtils.createToken(userDetails);

            return new ResponseEntity<>(new AuthResponseDto(jwt), HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
