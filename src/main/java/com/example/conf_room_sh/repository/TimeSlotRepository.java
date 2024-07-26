package com.example.conf_room_sh.repository;

import com.example.conf_room_sh.entity.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {
    Page<TimeSlot> findAllByRoomIdAndStartAfterAndEndBefore (UUID roomId, LocalDateTime startOfPeriod, LocalDateTime endOfPeriod, Pageable pageable);
    //TODO: Посмотреть, что он нагенерирует и подумать над оптимальностью

    Set<TimeSlot> findAllByStartAfterOrStartEqualsAndEndBeforeOrEndEqual(LocalDateTime start, LocalDateTime end);
    // TODO: Я не уверена, что это норм работать будет. Мб нужно будет переписать ручками
}
