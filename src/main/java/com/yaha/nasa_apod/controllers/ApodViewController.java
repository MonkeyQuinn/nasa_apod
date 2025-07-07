package com.yaha.nasa_apod.controllers;

import com.yaha.nasa_apod.model.view.ApodPageView;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Locale;

@Controller
@RequestMapping("/apod")
@RequiredArgsConstructor
@Slf4j
public class ApodViewController {

    private final ApodService apodService;

    @GetMapping
    public String apod(Locale locale) {
        return apodService.getLastApod(locale)
                .map(apod -> "redirect:/apod/" + apod.getDate())
                .orElse("redirect:/apod/none");
    }

    @GetMapping("/{date}")
    public String apod(@PathVariable String date, Locale locale, Model model) {
        ApodPageView apodPageView = apodService.getApodPageView(locale, LocalDate.parse(date));

        model.addAttribute("apod", apodPageView.apodDto());
        model.addAttribute("previousDate", apodPageView.previousDate());
        model.addAttribute("nextDate", apodPageView.nextDate());

        return "apod";
    }

}
