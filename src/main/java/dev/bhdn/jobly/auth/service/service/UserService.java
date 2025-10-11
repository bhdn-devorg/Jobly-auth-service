package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.user.UserDto;
import dev.bhdn.jobly.auth.service.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserDto userDto);

    UserResponseDto updateUser(Long id, UserDto userDto);

    void deleteUserById(Long id);
}
