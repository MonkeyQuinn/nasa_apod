package com.yaha.nasa_apod.controllers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apod")
@RequiredArgsConstructor
public class ApodApiController {

    private final ApodService apodService;

    @GetMapping
    public ApodDto getApod() {
        return apodService.getApodFromNasa();
    }

}
