package com.dictionary.application.dto;

import lombok.*;

@Setter
@Getter
public class AuthResponse {
    private String accessToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}