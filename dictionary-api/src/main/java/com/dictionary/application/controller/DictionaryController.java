package com.dictionary.application.controller;

import com.dictionary.application.dto.SiteResponseDto;
import com.dictionary.application.service.DictionaryService;
import com.dictionary.common.constant.RouteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Arrays.stream;

@RestController
@RequestMapping(RouteMapping.DICTIONARY_API_ROOT)
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping(RouteMapping.GET_DICTIONARY)
    public ResponseEntity<SiteResponseDto> getDictionary() {
        return new ResponseEntity<>(new SiteResponseDto(dictionaryService.getAllSiteDtos()), HttpStatus.OK);
    }

}
