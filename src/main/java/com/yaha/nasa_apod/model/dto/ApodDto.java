package com.yaha.nasa_apod.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApodDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String explanation;
    private String hdurl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;
    private String copyright;

}
