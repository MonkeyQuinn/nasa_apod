package com.yaha.nasa_apod.controllers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apod")
@RequiredArgsConstructor
public class ApodApiController {

    private final ApodService apodService;

    @GetMapping
    public ApodDto getApod() {
        return apodService.getLastApod().orElse(new ApodDto());
    }

    @GetMapping("/all")
    public List<ApodDto> getApods() {
        return apodService.getAllApods();
    }

}
