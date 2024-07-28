package com.example.conf_room_sh.repository;

import com.example.conf_room_sh.entity.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {
    Page<TimeSlot> findAllByRoomIdAndStartAfterAndEndBefore (UUID roomId, LocalDateTime startOfPeriod, LocalDateTime endOfPeriod, Pageable pageable);
    //TODO: Посмотреть, что он нагенерирует и подумать над оптимальностью

    @Query("SELECT ts FROM TimeSlot ts WHERE (ts.start >= :start) AND (ts.end <= :end)")
    Set<TimeSlot> findAllWithinRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
