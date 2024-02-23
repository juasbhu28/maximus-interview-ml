package com.dictionary.application.controller;

import com.dictionary.application.dto.SiteResponseDto;
import com.dictionary.application.dto.StatsDto;
import com.dictionary.application.service.StatsService;
import com.dictionary.common.constant.RouteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RouteMapping.STATS_API_ROOT)
public class StatController {

    @Autowired
    private StatsService statsService;

    @GetMapping(RouteMapping.GET_STATS)
    public ResponseEntity<List<StatsDto>> getStats() {
        return new ResponseEntity<>(statsService.getAllStats(), HttpStatus.OK);
    }

}
