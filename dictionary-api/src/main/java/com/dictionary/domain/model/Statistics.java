package com.dictionary.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class Statistics {
    private int id;
    private UUID requestId;
    private int postCount;
    private Timestamp processedAt;
}
