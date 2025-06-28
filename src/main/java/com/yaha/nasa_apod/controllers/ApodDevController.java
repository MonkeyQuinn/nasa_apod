package com.yaha.nasa_apod.controllers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev/apod")
@RequiredArgsConstructor
public class ApodDevController {

    private final ApodService apodService;

    @PostMapping
    public ApodDto postApod() {
        ApodDto apodDto = apodService.getApodFromNasa();
        apodService.saveApod(apodDto);
        return apodDto;
    }

    @PostMapping("/{date}")
    public ApodDto postApod(@PathVariable String date) {
        ApodDto apodDto = apodService.getApodFromNasaByDate(date);
        apodService.saveApod(apodDto);
        return apodDto;
    }

    @PostMapping("/{startDate}/{endDate}")
    public List<ApodDto> postApods(@PathVariable String startDate, @PathVariable String endDate) {
        List<ApodDto> apodDtos = apodService.getApodsFromNasaByDateRange(startDate, endDate);
        apodDtos.forEach(apodService::saveApod);
        return apodDtos;
    }

}
