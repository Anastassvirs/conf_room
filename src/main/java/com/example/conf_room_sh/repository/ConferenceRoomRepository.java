package com.example.conf_room_sh.repository;

import com.example.conf_room_sh.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, UUID> {
}
