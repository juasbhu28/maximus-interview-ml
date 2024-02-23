package com.dictionary.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Site {
    private Integer id;
    private String code;
    private Set<Word> words;
}
