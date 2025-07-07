package com.yaha.nasa_apod.controllers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/apod")
@RequiredArgsConstructor
public class ApodApiController {

    private final ApodService apodService;

    @GetMapping
    public ApodDto getApod(Locale locale) {
        return apodService.getLastApod(locale).orElse(new ApodDto());
    }

    @GetMapping("/all")
    public List<ApodDto> getApods(Locale locale) {
        return apodService.getAllApods(locale);
    }

}
