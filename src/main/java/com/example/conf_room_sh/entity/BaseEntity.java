package com.example.conf_room_sh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<P extends Serializable> implements Persistable<P>, Serializable {
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    protected P id;

    @Override
    public P getId() {
        return id;
    }

    public BaseEntity<P> setId(P id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
