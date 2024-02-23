package com.dictionary.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SiteDto {

    private String site;
    private String[] words;

}
