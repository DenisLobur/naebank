package com.naebank.bank.mapper;

import com.naebank.bank.controller.dto.CardDto;
import com.naebank.bank.repository.entity.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDto toDto(CardEntity cardEntity);

    CardEntity toEntity(CardDto cardDto);

    List<CardDto> toDtoList(List<CardEntity> cardEntityList);
}
