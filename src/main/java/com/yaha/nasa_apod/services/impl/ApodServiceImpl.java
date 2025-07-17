package com.yaha.nasa_apod.services.impl;

import com.deepl.api.DeepLClient;
import com.deepl.api.DeepLException;
import com.yaha.nasa_apod.clients.NasaClient;
import com.yaha.nasa_apod.mappers.ApodMapper;
import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.model.entities.Apod;
import com.yaha.nasa_apod.model.entities.ApodTranslation;
import com.yaha.nasa_apod.model.view.ApodPageView;
import com.yaha.nasa_apod.repositories.ApodRepository;
import com.yaha.nasa_apod.repositories.ApodTranslationRepository;
import com.yaha.nasa_apod.services.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ApodServiceImpl implements ApodService {

    private final NasaClient nasaClient;
    private final DeepLClient deepLClient;
    private final ApodRepository apodRepo;
    private final ApodTranslationRepository apodTransRepo;
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
        try {
            Apod apod = apodMapper.toApod(apodDto);
            apodRepo.save(apod);

            ApodDto localizedApodDto = new ApodDto();
            localizedApodDto.setTitle(deepLClient.translateText(apodDto.getTitle(), null, "ru").getText());
            localizedApodDto.setExplanation(deepLClient.translateText(apodDto.getExplanation(), null, "ru").getText());
            apodTransRepo.save(apodMapper.toTranslation(localizedApodDto, new Locale("ru"), apod));

        } catch (DeepLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ApodDto> getLastApod(Locale locale) {
        return withLocale(
                locale,
                () -> apodRepo.findTopByOrderByDateDesc().map(apodMapper::toDto),
                () -> apodTransRepo.findTopByLocale(
                                locale.getLanguage(),
                                Sort.by(Sort.Direction.DESC, "apod.date"))
                        .map(this::fromTransToDto)
        );
    }

    @Override
    public Optional<ApodDto> getApodByDate(Locale locale, LocalDate date) {
        return withLocale(
                locale,
                () -> apodRepo.findByDate(date).map(apodMapper::toDto),
                () -> apodTransRepo.findByLocaleAndApod_Date(locale.getLanguage(), date).map(this::fromTransToDto)
        );
    }

    @Override
    public List<ApodDto> getAllApods(Locale locale) {
        return withLocale(
                locale,
                () -> apodRepo
                        .findAll(Sort.by(Sort.Direction.DESC, "date"))
                        .stream()
                        .map(apodMapper::toDto)
                        .toList(),
                () -> apodTransRepo.findAllByLocale(
                                locale.getLanguage(),
                                Sort.by(Sort.Direction.DESC, "apod.date")
                        ).stream()
                        .map(this::fromTransToDto)
                        .toList()
        );
    }

    @Override
    public Optional<String> getPreviousDate(Locale locale, LocalDate date) {
        return withLocale(
                locale,
                () -> apodRepo
                        .findTopByDateBefore(date, Sort.by(Sort.Direction.DESC, "date"))
                        .map(apod -> apod.getDate().toString()),
                () -> apodTransRepo
                        .findPrevDateByLocale(locale.getLanguage(), date)
                        .map(LocalDate::toString)
        );
    }

    @Override
    public Optional<String> getNextDate(Locale locale, LocalDate date) {
        return withLocale(
                locale,
                () -> apodRepo
                        .findTopByDateAfter(date, Sort.by(Sort.Direction.ASC, "date"))
                        .map(apod -> apod.getDate().toString()),
                () -> apodTransRepo
                        .findNextDateByLocale(locale.getLanguage(), date)
                        .map(LocalDate::toString)
        );
    }

    @Override
    public ApodPageView getApodPageView(Locale locale, LocalDate date) {
        ApodDto apodDto = getApodByDate(locale, date).orElse(new ApodDto());
        String prevDate = getPreviousDate(locale, date).orElse(null);
        String nextDate = getNextDate(locale, date).orElse(null);

        return new ApodPageView(apodDto, prevDate, nextDate);
    }

    private <T> T withLocale(
            Locale locale,
            Supplier<? extends T> enSupplier,
            Supplier<? extends T> transSupplier
    ) {
        return locale == null || locale.getLanguage().equalsIgnoreCase("en")
                ? enSupplier.get() : transSupplier.get();
    }

    private ApodDto fromTransToDto(ApodTranslation apodTranslation) {
        return apodMapper.toLocalizedDto(
                apodTranslation.getApod(),
                apodTranslation
        );
    }

}
