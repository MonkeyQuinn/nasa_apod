package com.yaha.nasa_apod.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class BasicApod extends BasicTimedEntityLarge {

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private String title;

    public BasicApod(Long id) {
        super(id);
    }

}
