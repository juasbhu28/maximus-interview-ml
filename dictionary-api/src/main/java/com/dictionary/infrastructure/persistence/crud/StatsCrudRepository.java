package com.dictionary.infrastructure.persistence.crud;

import com.dictionary.infrastructure.persistence.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsCrudRepository extends JpaRepository<StatsEntity, Long> {
}
