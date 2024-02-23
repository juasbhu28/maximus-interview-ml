package com.dictionary.application.service;

import com.dictionary.application.dto.StatsDto;
import com.dictionary.application.mapper.StatsDtoMapper;
import com.dictionary.domain.mapper.StatsMapper;
import com.dictionary.infrastructure.persistence.repository.StatsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatsService {

    @Autowired
    private StatsRepositoryImpl statsRepository;

    @Autowired
    private StatsDtoMapper statsMapper;

    public List<StatsDto> getAllStats() {
        return statsMapper.toDto(statsRepository.getAll());
    }
}
