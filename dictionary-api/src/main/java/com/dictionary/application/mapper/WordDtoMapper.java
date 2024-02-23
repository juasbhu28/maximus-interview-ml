package com.dictionary.application.mapper;

import com.dictionary.application.dto.WordDto;
import com.dictionary.domain.model.Word;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WordDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "word", source = "word")
    Word toModel(WordDto dto);

    @InheritInverseConfiguration
    WordDto toDto(Word model);

}

