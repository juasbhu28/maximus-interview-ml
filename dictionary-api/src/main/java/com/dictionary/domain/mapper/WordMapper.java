package com.dictionary.domain.mapper;


import com.dictionary.domain.model.Word;
import com.dictionary.infrastructure.persistence.entity.WordEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WordMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "word", source = "word")
    Word toModel(WordEntity entity);

    @InheritInverseConfiguration
    WordEntity toEntity(Word model);
}