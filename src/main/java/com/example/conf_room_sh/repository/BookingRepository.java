package com.example.conf_room_sh.repository;

import com.example.conf_room_sh.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
