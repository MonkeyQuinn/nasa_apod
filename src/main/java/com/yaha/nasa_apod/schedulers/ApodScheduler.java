package com.yaha.nasa_apod.schedulers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApodScheduler {

    private final ApodService apodService;

    @Scheduled(cron = "0 0 0/5 * * *")
    public void fetchAndSaveApod() {
        try {
            ApodDto apodDto = apodService.getApodFromNasa();

            if (apodService.getApodByDate(apodDto.getDate()).isEmpty()) {
                apodService.saveApod(apodDto);
                log.info("Saved apod {}", apodDto);

            } else {
                log.info("Apod {} already exists", apodDto);
            }

        } catch (Exception e) {
            log.error("Failed to fetch/save apod: {}", e.getMessage(), e);
        }
    }

}
