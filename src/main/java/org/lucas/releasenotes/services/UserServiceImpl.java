package org.lucas.releasenotes.services;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NullMarked;
import org.lucas.releasenotes.dtos.UserRequestDto;
import org.lucas.releasenotes.dtos.UserResponseDto;
import org.lucas.releasenotes.dtos.UserUpdateDto;
import org.lucas.releasenotes.exceptions.ResourceAlreadyExistsException;
import org.lucas.releasenotes.exceptions.ResourceNotFoundException;
import org.lucas.releasenotes.mappers.UserMapper;
import org.lucas.releasenotes.models.User;
import org.lucas.releasenotes.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  final private UserRepository userRepository;
  final private UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  @NullMarked
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
            .map(user -> org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER")
                    .build())
            .orElseThrow(() -> new UsernameNotFoundException("User not Found with Username: " + username));
  }


  @Override
  @Transactional
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    if (userRepository.existsByEmail(userRequestDto.email())) {
      throw new ResourceAlreadyExistsException("Email Already Exists");
    }
    User user = userMapper.toEntity(userRequestDto);
    User savedUser = userRepository.save(user);
    return userMapper.userToUserResponseDto(savedUser);
  }

  @Override
  public UserResponseDto getUserById(UUID id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not Found"));
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
  @Transactional
  public UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not Found"));
    userMapper.updateUser(userUpdateDto, user);
    User updatedUser = userRepository.save(user);
    return userMapper.userToUserResponseDto(updatedUser);
  }

  @Override
  @Transactional
  public void deleteUser(UUID id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("User not Found with ID: " + id);
    }
  }

}
