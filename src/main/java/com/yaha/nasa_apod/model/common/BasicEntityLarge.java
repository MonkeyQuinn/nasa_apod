package com.yaha.nasa_apod.model.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class BasicEntityLarge implements Serializable, BaseEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BasicEntityLarge(Long id) {
        this.id = id;
    }

}
