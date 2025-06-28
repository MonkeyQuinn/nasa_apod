package com.yaha.nasa_apod.controllers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apod")
@RequiredArgsConstructor
public class ApodViewController {

    private final ApodService apodService;

    @GetMapping
    public String apod(Model model) {
        model.addAttribute("apod", apodService.getLastApod().orElse(new ApodDto()));
        return "apod";
    }

}
