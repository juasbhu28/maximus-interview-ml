package com.dictionary.domain.repository;

import com.dictionary.domain.model.Stats;

import java.util.List;

public interface IStatsRepository {
    List<Stats> getAll();
    void save(Stats stats);
}
