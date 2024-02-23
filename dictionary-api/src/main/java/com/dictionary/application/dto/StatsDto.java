package com.dictionary.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDto {
    private String requestId;
    private String request;
    private String response;
    private String executionTime;
}
