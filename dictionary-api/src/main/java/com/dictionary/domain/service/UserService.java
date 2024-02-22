package com.dictionary.domain.service;

import com.dictionary.domain.entity.UserEntity;
import com.dictionary.domain.repository.IUserRepository;
import com.dictionary.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserRepository {

    @Autowired
    private IUserRepository userRepository;

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
