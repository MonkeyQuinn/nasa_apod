package com.yaha.nasa_apod.services;

import com.yaha.nasa_apod.model.dto.ApodDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApodService {

    ApodDto getApodFromNasa();

    ApodDto getApodFromNasaByDate(String date);

    List<ApodDto> getApodsFromNasaByDateRange(String startDate, String endDate);

    void saveApod(ApodDto apodDto);

    Optional<ApodDto> getLastApod();

    Optional<ApodDto> getApodByDate(LocalDate date);

    List<ApodDto> getAllApods();

    Optional<String> getPreviousDate(LocalDate date);

    Optional<String> getNextDate(LocalDate date);

}
