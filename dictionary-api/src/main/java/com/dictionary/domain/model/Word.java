package com.dictionary.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Word {
    private Integer id;
    private String word;
}
