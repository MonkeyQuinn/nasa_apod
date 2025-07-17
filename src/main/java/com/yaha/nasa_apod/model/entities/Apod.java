package com.yaha.nasa_apod.model.entities;

import com.yaha.nasa_apod.model.common.BasicApod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Apod extends BasicApod {

    @Column(unique = true)
    private LocalDate date;

    private String serviceVersion;
    private String mediaType;
    private String hdurl;
    private String url;
    private String copyright;

    @OneToMany(mappedBy = "apod", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ApodTranslation> translations;

}
