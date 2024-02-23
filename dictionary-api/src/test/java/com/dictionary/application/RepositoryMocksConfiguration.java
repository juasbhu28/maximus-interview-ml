package com.dictionary.application;

import com.dictionary.application.service.UserSecurityService;
import com.dictionary.infrastructure.persistence.crud.SiteCrudRepository;
import com.dictionary.infrastructure.persistence.crud.StatsCrudRepository;
import com.dictionary.infrastructure.persistence.crud.UserCrudRepository;
import com.dictionary.infrastructure.web.security.jwt.JwtFilter;
import com.dictionary.infrastructure.web.security.jwt.JwtUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class RepositoryMocksConfiguration {

    @MockBean
    private SiteCrudRepository siteCrudRepository;

    @MockBean
    private UserCrudRepository userCrudRepository;

    @MockBean
    private StatsCrudRepository statsCrudRepository;


}