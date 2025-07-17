package com.yaha.nasa_apod.model.entities;

import com.yaha.nasa_apod.model.common.BasicApod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ApodTranslation extends BasicApod {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Apod apod;

    @Column(length = 2, nullable = false)
    private String locale;

}
