package com.dictionary.infrastructure.persistence.crud;

import com.dictionary.infrastructure.persistence.entity.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SiteCrudRepository extends JpaRepository<SiteEntity, Long> {

}
