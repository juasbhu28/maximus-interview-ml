package com.dictionary.domain.mapper;

import com.dictionary.domain.model.Role;
import com.dictionary.domain.model.User;
import com.dictionary.infrastructure.persistence.entity.RoleEntity;
import com.dictionary.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "disabled", ignore = true)
    UserEntity toEntity(User user);

    @InheritInverseConfiguration
    User toModel(UserEntity userEntity);

}
