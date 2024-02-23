package com.dictionary.application.dto;

import lombok.*;

@Setter
@Getter
public class AuthResponseDto {
    private String accessToken;

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}