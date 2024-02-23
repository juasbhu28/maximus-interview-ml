package com.dictionary.application.controller;

import com.dictionary.application.dto.ValidateRequestDto;
import com.dictionary.application.dto.ValidateResponseDto;
import com.dictionary.application.service.ValidateService;
import com.dictionary.common.constant.RouteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RouteMapping.VALIDATE_API_ROOT)
public class ValidateController {

    @Autowired
    private ValidateService validateService;

    @PostMapping(RouteMapping.VALIDATE)
    public ResponseEntity<ValidateResponseDto> validate(@RequestBody ValidateRequestDto request){

        return ResponseEntity.ok(validateService.validate(request));
    }
}
