package com.dictionary.domain.mapper;

import com.dictionary.domain.model.Site;
import com.dictionary.infrastructure.persistence.entity.SiteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring", uses = WordMapper.class)
public interface SiteMapper {

        SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

        @Mapping(target = "id", source = "id")
        @Mapping(target = "code", source = "code")
        @Mapping(target = "words", source = "words")
        @Mapping(target = "description", ignore = true)
        SiteEntity toEntity(Site model);

        @InheritInverseConfiguration
        Site toModel(SiteEntity entity);
        List<Site> toModel(List<SiteEntity> entity);
}
