package com.yaha.nasa_apod.services;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.model.view.ApodPageView;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface ApodService {

    ApodDto getApodFromNasa();

    ApodDto getApodFromNasaByDate(String date);

    List<ApodDto> getApodsFromNasaByDateRange(String startDate, String endDate);

    void saveApod(ApodDto apodDto);

    Optional<ApodDto> getLastApod(Locale locale);

    Optional<ApodDto> getApodByDate(Locale locale, LocalDate date);

    List<ApodDto> getAllApods(Locale locale);

    Optional<String> getPreviousDate(Locale locale, LocalDate date);

    Optional<String> getNextDate(Locale locale, LocalDate date);

    ApodPageView getApodPageView(Locale locale, LocalDate date);

}
