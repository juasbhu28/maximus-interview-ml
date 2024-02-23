package com.dictionary.domain.mapper;

import com.dictionary.domain.model.Role;
import com.dictionary.infrastructure.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    public abstract RoleEntity toEntity(Role role);

    public abstract Role toModel(RoleEntity roleEntity);
}
