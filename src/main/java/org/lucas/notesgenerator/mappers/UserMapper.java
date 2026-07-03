package org.lucas.notesgenerator.mappers;

import org.lucas.notesgenerator.dtos.UserRequestDto;
import org.lucas.notesgenerator.dtos.UserResponseDto;
import org.lucas.notesgenerator.dtos.UserUpdateDto;
import org.lucas.notesgenerator.models.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponseDto userToUserResponseDto(User user);

  User toEntity(UserRequestDto userRequestDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateUser(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
