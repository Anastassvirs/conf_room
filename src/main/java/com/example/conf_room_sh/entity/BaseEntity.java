package com.example.conf_room_sh.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<P extends Serializable> implements Persistable<P>, Serializable {
    @Serial
    private static final long serialVersionUID = -638293462837463784L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
