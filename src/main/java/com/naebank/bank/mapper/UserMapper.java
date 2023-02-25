package com.naebank.bank.mapper;

import com.naebank.bank.controller.dto.UserDto;
import com.naebank.bank.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<UserEntity> userEntityList);
}
