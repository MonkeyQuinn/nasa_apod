package com.yaha.nasa_apod.services.impl;

import com.yaha.nasa_apod.clients.NasaClient;
import com.yaha.nasa_apod.mappers.ApodMapper;
import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.model.entities.Apod;
import com.yaha.nasa_apod.repositories.ApodRepository;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApodServiceImpl implements ApodService {

    private final NasaClient nasaClient;
    private final ApodRepository apodRepository;
    private final ApodMapper apodMapper;

    @Override
    public ApodDto getApodFromNasa() {
        return nasaClient.fetchApod();
    }

    @Override
    public ApodDto getApodFromNasaByDate(String date) {
        return nasaClient.fetchApodByDate(date);
    }

    @Override
    public List<ApodDto> getApodsFromNasaByDateRange(String startDate, String endDate) {
        return nasaClient.fetchApodsByDateRange(startDate, endDate);
    }

    @Override
    public void saveApod(ApodDto apodDto) {
        apodRepository.save(apodMapper.toEntity(apodDto));
    }

    @Override
    public Optional<ApodDto> getLastApod() {
        return apodRepository.findTopByOrderByDateDesc()
                .map(apodMapper::toDto);
    }

    @Override
    public Optional<ApodDto> getApodByDate(LocalDate date) {
        return apodRepository.findByDate(date)
                .map(apodMapper::toDto);
    }

    @Override
    public List<ApodDto> getAllApods() {
        return apodRepository.findAllByOrderByDateDesc().stream()
                .map(apodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<String> getPreviousDate(LocalDate date) {
        return apodRepository.findTopByDateBeforeOrderByDateDesc(date)
                .map(apod -> apod.getDate().toString());
    }

    @Override
    public Optional<String> getNextDate(LocalDate date) {
        return apodRepository.findTopByDateAfterOrderByDateAsc(date)
                .map(apod -> apod.getDate().toString());
    }

}
