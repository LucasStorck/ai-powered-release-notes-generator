package org.lucas.notesgenerator.services;

import org.lucas.notesgenerator.dtos.UserRequestDto;
import org.lucas.notesgenerator.dtos.UserResponseDto;
import org.lucas.notesgenerator.dtos.UserUpdateDto;
import org.lucas.notesgenerator.mappers.UserMapper;
import org.lucas.notesgenerator.models.User;
import org.lucas.notesgenerator.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  final private UserRepository userRepository;
  final private UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    User user = userMapper.toEntity(userRequestDto);
    User savedUser = userRepository.save(user);
    return userMapper.userToUserResponseDto(savedUser);
  }

  @Override
  public UserResponseDto getUserById(UUID id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not Found"));
    return userMapper.userToUserResponseDto(user);
  }

  @Override
  public List<UserResponseDto> getAllUsers() {
    return userRepository.findAll()
            .stream()
            .map(userMapper::userToUserResponseDto)
            .toList();
  }

  @Override
  public UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not Found"));
    userMapper.updateUser(userUpdateDto, user);
    User updatedUser = userRepository.save(user);
    return userMapper.userToUserResponseDto(updatedUser);
  }

  @Override
  public void deleteUser(UUID id) {
    userRepository.deleteById(id);
  }
}
