package org.lucas.releasenotes.mappers;

import org.lucas.releasenotes.dtos.UserRequestDto;
import org.lucas.releasenotes.dtos.UserResponseDto;
import org.lucas.releasenotes.dtos.UserUpdateDto;
import org.lucas.releasenotes.models.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponseDto toResponseDto(User user);

  User toEntity(UserRequestDto userRequestDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateUser(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
