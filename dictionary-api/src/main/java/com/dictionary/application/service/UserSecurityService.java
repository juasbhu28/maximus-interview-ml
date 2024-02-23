package com.dictionary.application.service;

import com.dictionary.domain.repository.IUserRepository;
import com.dictionary.infrastructure.persistence.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepositoryImpl userRepository;

    @Autowired
    public UserSecurityService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.dictionary.domain.model.User userEntity = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("User not found "));

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

}