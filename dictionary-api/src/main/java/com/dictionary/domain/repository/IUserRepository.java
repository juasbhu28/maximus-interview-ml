package com.dictionary.domain.repository;

import com.dictionary.domain.model.User;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByUsername(String username);
}
