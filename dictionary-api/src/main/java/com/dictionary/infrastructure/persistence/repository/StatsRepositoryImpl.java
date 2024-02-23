package com.dictionary.infrastructure.persistence.repository;

import com.dictionary.domain.mapper.StatsMapper;
import com.dictionary.domain.model.Stats;
import com.dictionary.domain.repository.IStatsRepository;
import com.dictionary.infrastructure.persistence.crud.StatsCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatsRepositoryImpl implements IStatsRepository{

    @Autowired
    private StatsCrudRepository statsCrudRepository;

    @Autowired
    private StatsMapper statsMapper;

    @Override
    public List<Stats> getAll() {
        return statsMapper.toModel(statsCrudRepository.findAll());
    }

    @Override
    public void save(Stats stats) {
        statsCrudRepository.save(statsMapper.toEntity(stats));
    }
}
