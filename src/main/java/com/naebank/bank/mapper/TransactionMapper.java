package com.naebank.bank.mapper;

import com.naebank.bank.controller.dto.TransactionDto;
import com.naebank.bank.repository.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDto toDto(TransactionEntity transactionEntity);

    TransactionEntity toEntity(TransactionDto transactionDto);

    List<TransactionDto> toDtoList(List<TransactionEntity> transactionEntityList);
}
