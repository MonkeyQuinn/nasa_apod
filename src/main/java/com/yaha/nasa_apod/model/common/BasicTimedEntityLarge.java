package com.yaha.nasa_apod.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class BasicTimedEntityLarge extends BasicEntityLarge {

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime createdAt;

    public BasicTimedEntityLarge(Long id) {
        super(id);
    }

}
