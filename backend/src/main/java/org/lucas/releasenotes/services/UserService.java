package org.lucas.releasenotes.services;

import org.lucas.releasenotes.dtos.UserRequestDto;
import org.lucas.releasenotes.dtos.UserResponseDto;
import org.lucas.releasenotes.dtos.UserUpdateDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto getUserById(UUID id);

  List<UserResponseDto> getAllUsers();

  UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto);

  void deleteUser(UUID id);
}
