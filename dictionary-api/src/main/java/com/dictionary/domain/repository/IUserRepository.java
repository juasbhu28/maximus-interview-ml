package com.dictionary.domain.repository;

import com.dictionary.application.model.User;
import com.dictionary.domain.entity.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findByUsername(String username);
}
