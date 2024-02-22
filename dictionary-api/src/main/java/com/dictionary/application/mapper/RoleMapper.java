package com.dictionary.application.mapper;

import com.dictionary.application.model.Role;
import com.dictionary.domain.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    public abstract RoleEntity toEntity(Role role);

    public abstract Role toModel(RoleEntity roleEntity);
}
