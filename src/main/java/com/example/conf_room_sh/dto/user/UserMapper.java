package com.example.conf_room_sh.dto.user;

import com.example.conf_room_sh.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail()
        );
    }

    public User toUser(UserDto userDto) {
        return new User(
                userDto.getName(),
                userDto.getEmail()
        );
    }

    public Page<UserDto> toPageUserDto(Page<User> usersPage) {
        return usersPage.map(this::toUserDto);
    }
}