package org.lucas.notesgenerator.mappers;

import org.lucas.notesgenerator.dtos.UserRequestDto;
import org.lucas.notesgenerator.dtos.UserResponseDto;
import org.lucas.notesgenerator.dtos.UserUpdateDto;
import org.lucas.notesgenerator.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  
  UserResponseDto userToUserResponseDto(User user);

  User toEntity(UserRequestDto userRequestDto);

  void updateUser(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
