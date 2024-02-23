package com.dictionary.application.service;

import com.dictionary.application.dto.ValidateRequestDto;
import com.dictionary.application.dto.ValidateResponseDto;
import com.dictionary.domain.model.Site;
import com.dictionary.domain.model.Word;
import com.dictionary.infrastructure.persistence.repository.SiteRepositoryImpl;
import com.dictionary.infrastructure.persistence.repository.StatsRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidateServiceTest {

    @Mock
    private SiteRepositoryImpl siteRepository;

    @Mock
    private StatsRepositoryImpl statsRepository;

    @InjectMocks
    private ValidateService validateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidateSuccess() throws Exception {
        // Arrange
        ValidateRequestDto request = new ValidateRequestDto();
        request.setPosts(new String[]{
                "hola parce, como esta usted?",
                "hola parce, vos que tal?",});

        Site site = new Site();
        site.setCode("CO");
        Word word1 = Word.builder().word("parce").build();
        Word word2 = Word.builder().word("parce").build();
        site.setWords(new HashSet<>(Arrays.asList(word1, word2)));
        List<Site> sites = List.of(site);
        when(siteRepository.getAll()).thenReturn(sites);

        // Act
        ValidateResponseDto response = validateService.validate(request);

        // Assert
        assertNotNull(response);
        assertTrue(response.getSites().containsKey("CO"));
        verify(statsRepository, times(1)).save(any());

    }

}
