package com.naebank.bank.mapper;

import com.naebank.bank.controller.dto.CardDto;
import com.naebank.bank.repository.entity.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    @Mapping(target = "amount", source = "amount")
    CardDto toDto(CardEntity cardEntity);

    CardEntity toEntity(CardDto cardDto);

    List<CardDto> toDtoList(List<CardEntity> cardEntityList);
}
