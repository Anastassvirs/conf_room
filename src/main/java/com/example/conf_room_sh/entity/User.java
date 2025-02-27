package com.example.conf_room_sh.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity<UUID> {
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @PrePersist
    protected void onPrePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 512)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_quests",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"))
    private Set<Booking> bookings = new HashSet<Booking>();
}
