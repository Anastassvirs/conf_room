package com.example.conf_room_sh.service.user;

import com.example.conf_room_sh.dto.user.UserDto;
import com.example.conf_room_sh.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<UserDto> getAllPageable(Pageable pageable);

    UserDto findById(UUID id);

    User getById(UUID id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UUID id, UserDto userDto);

    void deleteById(UUID id);

    boolean emailAlreadyExist(String email, UUID id);

    boolean userExistById(UUID id);
}