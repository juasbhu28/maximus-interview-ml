package com.dictionary.application.mapper;

import com.dictionary.application.model.Role;
import com.dictionary.application.model.User;
import com.dictionary.domain.entity.RoleEntity;
import com.dictionary.domain.entity.UserEntity;
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

    default RoleEntity map(Role role) {
        if (role == null) {
            return null;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());
        return roleEntity;
    }

    default Role map(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        return role;
    }

}
