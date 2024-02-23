package com.dictionary.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidateResponseDto {
    private Map<String, PostDTO> sites;
}
