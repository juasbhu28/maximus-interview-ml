package com.dictionary.application.service;

import com.dictionary.application.dto.SiteDto;
import com.dictionary.application.mapper.DictionaryMapper;
import com.dictionary.domain.mapper.SiteMapper;
import com.dictionary.domain.model.Site;
import com.dictionary.infrastructure.persistence.repository.SiteRepositoryImpl;
import com.dictionary.infrastructure.persistence.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DictionaryService {

    private final SiteRepositoryImpl siteRepository;
    private final DictionaryMapper dictionaryMapper;

    @Autowired
    public DictionaryService(SiteRepositoryImpl siteRepository, DictionaryMapper dictionaryMapper) {
        this.siteRepository = siteRepository;
        this.dictionaryMapper = dictionaryMapper;
    }

    public List<SiteDto> getAllSiteDtos() {
        List<Site> sites = siteRepository.getAll();
        return dictionaryMapper.toSiteDto(sites);
    }
}
