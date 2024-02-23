package com.dictionary.application.mapper;

import com.dictionary.application.dto.SiteDto;
import com.dictionary.application.dto.WordDto;
import com.dictionary.domain.model.Site;
import com.dictionary.domain.model.Word;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = WordDtoMapper.class)
public interface DictionaryMapper {

    DictionaryMapper INSTANCE = Mappers.getMapper(DictionaryMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "site")
    @Mapping(target = "words", source = "words", qualifiedByName = "mapStringsToWords")
    Site toSite(SiteDto siteDto);

    @Named("mapStringsToWords")
    default Set<Word> mapStringsToWords(String[] words) {
        return Arrays.stream(words)
                .map(word -> Word.builder().word(word).build())
                .collect(Collectors.toSet());
    }

    @InheritInverseConfiguration
    @Mapping(target = "words", source = "words", qualifiedByName = "mapWordsToStrings")
    SiteDto toSiteDto(Site site);
    List<SiteDto> toSiteDto(List<Site> site);

    @Named("mapWordsToStrings")
    default String[] mapWordsToStrings(Set<Word> words) {
        return words.stream().map(Word::getWord).toArray(String[]::new);
    }
}
