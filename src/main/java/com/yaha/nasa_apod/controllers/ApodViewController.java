package com.yaha.nasa_apod.controllers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/apod")
@RequiredArgsConstructor
public class ApodViewController {

    private final ApodService apodService;

    @GetMapping
    public String apod() {
        return apodService.getLastApod()
                .map(apod -> "redirect:/apod/" + apod.getDate())
                .orElse("redirect:/apod/none");
    }

    @GetMapping("/{date}")
    public String apod(Model model, @PathVariable String date) {
        apodService.getApodByDate(LocalDate.parse(date))
                .ifPresentOrElse(
                        apodDto -> model.addAttribute("apod", apodDto),
                        () -> model.addAttribute("apod",
                                apodService.getLastApod().orElse(new ApodDto()))
                );

        model.addAttribute("previousDate",
                apodService.getPreviousDate(LocalDate.parse(date)).orElse(null));
        model.addAttribute("nextDate",
                apodService.getNextDate(LocalDate.parse(date)).orElse(null));

        return "apod";
    }

}
