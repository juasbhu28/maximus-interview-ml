package com.dictionary.infrastructure.persistence.repository;

import com.dictionary.domain.mapper.SiteMapper;
import com.dictionary.domain.model.Site;
import com.dictionary.domain.repository.ISiteRepository;
import com.dictionary.infrastructure.persistence.crud.SiteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SiteRepositoryImpl implements ISiteRepository {

    @Autowired
    private SiteCrudRepository siteCrudRepository;

    @Autowired
    private SiteMapper siteMapper;

    @Override
    public List<Site> getAll() {
        return siteMapper.toModel(siteCrudRepository.findAll());
    }
}
