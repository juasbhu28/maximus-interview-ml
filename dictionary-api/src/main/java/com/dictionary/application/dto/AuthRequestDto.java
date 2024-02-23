package com.dictionary.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
}
