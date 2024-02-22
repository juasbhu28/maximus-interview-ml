package com.dictionary.application.crud;

import com.dictionary.application.model.User;
import com.dictionary.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserCrudRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
