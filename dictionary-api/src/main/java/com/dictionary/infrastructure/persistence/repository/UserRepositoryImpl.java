package com.dictionary.infrastructure.persistence.repository;

import com.dictionary.infrastructure.persistence.crud.UserCrudRepository;
import com.dictionary.domain.mapper.UserMapper;
import com.dictionary.domain.model.User;
import com.dictionary.domain.repository.IUserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements IUserRepository{

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return userCrudRepository.findByUsername(username).map(userMapper::toModel);
    }
}