package com.dictionary.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Stats {

    private Integer id;
    private String requestId;
    private String request;
    private String response;
    private long executionTime;
    private Date createdAt;

}
