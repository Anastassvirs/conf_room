package com.example.conf_room_sh.repository;

import com.example.conf_room_sh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}