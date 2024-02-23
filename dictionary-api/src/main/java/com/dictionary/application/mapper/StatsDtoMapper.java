package com.dictionary.application.mapper;

import com.dictionary.application.dto.StatsDto;
import com.dictionary.domain.model.Stats;
import com.dictionary.domain.model.Word;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StatsDtoMapper {

    @Mapping(target = "requestId", source = "requestId")
    @Mapping(target = "request", source = "request")
    @Mapping(target = "response", source = "response")
    @Mapping(target = "executionTime", source = "executionTime", qualifiedByName = "timeToMilliseconds")
    StatsDto toDto(Stats model);
    List<StatsDto> toDto(List<Stats> entity);

    @Named("timeToMilliseconds")
    default String timeToMilliseconds(long time) {
       return String.valueOf(time) + "ms";
    }


}
