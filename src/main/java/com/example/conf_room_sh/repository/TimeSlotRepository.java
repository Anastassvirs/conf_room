package com.example.conf_room_sh.repository;

import com.example.conf_room_sh.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {
}
