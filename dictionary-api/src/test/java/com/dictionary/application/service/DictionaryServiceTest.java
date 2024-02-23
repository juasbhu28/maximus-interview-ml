package com.dictionary.application.service;

import com.dictionary.application.dto.SiteDto;
import com.dictionary.application.mapper.DictionaryMapper;
import com.dictionary.domain.model.Site;
import com.dictionary.infrastructure.persistence.repository.SiteRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DictionaryServiceTest {

    @Mock
    private SiteRepositoryImpl siteRepository;

    @Mock
    private DictionaryMapper dictionaryMapper;

    @InjectMocks
    private DictionaryService dictionaryService;



    @Test
    void getAllSiteDtos_whenCalled_thenReturnsSiteDtos() {
        // Preparar
        Site site1 = new Site();
        Site site2 = new Site();
        List<Site> sites = Arrays.asList(site1, site2);
        SiteDto siteDto1 = new SiteDto();
        SiteDto siteDto2 = new SiteDto();
        List<SiteDto> siteDtos = Arrays.asList(siteDto1, siteDto2);

        when(siteRepository.getAll()).thenReturn(sites);
        when(dictionaryMapper.toSiteDto(sites)).thenReturn(siteDtos);

        // Actuar
        List<SiteDto> result = dictionaryService.getAllSiteDtos();

        // Verificar
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(siteDtos);
    }
}