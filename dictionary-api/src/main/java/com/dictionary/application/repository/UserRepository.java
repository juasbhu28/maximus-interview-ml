package com.dictionary.application.repository;

import com.dictionary.application.crud.UserCrudRepository;
import com.dictionary.application.mapper.UserMapper;
import com.dictionary.domain.entity.UserEntity;
import com.dictionary.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository{

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userCrudRepository.findByUsername(username).map(userMapper::toEntity);
    }
}