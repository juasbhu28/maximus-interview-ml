package com.dictionary.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO {
    private int total;
    private List<String> posts;
}
