package com.dictionary.application;

import com.dictionary.infrastructure.persistence.crud.SiteCrudRepository;
import com.dictionary.infrastructure.persistence.crud.UserCrudRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class RepositoryMocksConfiguration {

    @MockBean
    private SiteCrudRepository siteCrudRepository;

    @MockBean
    private UserCrudRepository userCrudRepository;

}