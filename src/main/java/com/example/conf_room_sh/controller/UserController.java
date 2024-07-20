package com.example.conf_room_sh.controller;

import com.example.conf_room_sh.dto.user.UserDto;
import com.example.conf_room_sh.service.user.UserService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<UserDto> findAll(@PageableDefault Pageable pageable) {
        return userService.getAllPageable(pageable);
    }

    public UserDto find(@PathVariable UUID userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PatchMapping(path = "/{userId}")
    public UserDto update(@PathVariable UUID userId, @RequestBody UserDto userDto) {
        return userService.update(userId, userDto);
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteById(@PathVariable UUID userId) {
        userService.deleteById(userId);
    }
}
