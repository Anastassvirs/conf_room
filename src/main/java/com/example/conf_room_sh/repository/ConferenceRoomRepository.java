package com.example.conf_room_sh.repository;

import com.example.conf_room_sh.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, UUID> {
}
