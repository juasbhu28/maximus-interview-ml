package com.dictionary.domain.mapper;

import com.dictionary.domain.model.Stats;
import com.dictionary.infrastructure.persistence.entity.StatsEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatsMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestId", source = "requestId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "request", source = "request")
    @Mapping(target = "response", source = "response")
    @Mapping(target = "executionTime", source = "executionTime")
    StatsEntity toEntity(Stats model);
    List<Stats> toModel(List<StatsEntity> entity);

    @InheritInverseConfiguration
    Stats toModel(StatsEntity entity);


}
