package com.example.conf_room_sh.service.user;

import com.example.conf_room_sh.dto.user.UserDto;
import com.example.conf_room_sh.dto.user.UserMapper;
import com.example.conf_room_sh.entity.User;
import com.example.conf_room_sh.exception.EmailException;
import com.example.conf_room_sh.exception.NotFoundAnythingException;
import com.example.conf_room_sh.exception.SaveEntityException;
import com.example.conf_room_sh.exception.WrongParametersException;
import com.example.conf_room_sh.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.userRepository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDto> getAllPageable(Pageable pageable) {
        return userMapper.toPageUserDto(userRepository.findAll(pageable));
    }

    @Override
    public UserDto findById(UUID id) {
        return userMapper.toUserDto(getById(id));
    }

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundAnythingException("Пользователя с данным id не существует"));
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.toUser(userDto);

        if (Objects.isNull(userDto.getEmail()) || !userDto.getEmail().contains("@") ||
                Objects.isNull(userDto.getName()) || userDto.getName().isEmpty() || userDto.getEmail().isEmpty()) {
            throw new WrongParametersException("Неправильно заполнены поля создаваемого пользователя");
        }
        log.debug("Добавлен новый пользователь: {}", user);
        try {
            return userMapper.toUserDto(userRepository.save(user));
        } catch (Exception e) {
            log.debug("Произошла ошибка: Неправильно заполнены поля создаваемого пользователя");
            throw new SaveEntityException("Неправильно заполнены поля создаваемого пользователя");
        }
    }

    @Transactional
    @Override
    public UserDto update(UUID id, UserDto userDto) {
        User user = getById(id);
        Optional.ofNullable(userDto.getName()).ifPresent(user::setName);
        if (userDto.getEmail() != null) {
            if (!emailAlreadyExist(userDto.getEmail(), id)) {
                try {
                    user.setEmail(userDto.getEmail());
                } catch (Exception e) {
                    throw new EmailException("Данный email уже зарегистрирован");
                }
            } else {
                throw new EmailException("Данный email уже зарегистрирован");
            }
        }
        if (userRepository.existsById(id)) {
            log.debug("Обновлен пользователь: {}", user);
            return userMapper.toUserDto(userRepository.save(user));
        } else {
            log.debug("Произошла ошибка: Введенного пользователя не существует");
            throw new NotFoundAnythingException("Такого пользователя не существует");
        }
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }



    @Override
    public boolean emailAlreadyExist(String email, UUID id) {
        for (User oldUser : userRepository.findAll()) {
            if (Objects.equals(oldUser.getEmail(), email)
                    && !Objects.equals(oldUser.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}