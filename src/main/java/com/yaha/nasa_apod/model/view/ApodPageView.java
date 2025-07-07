package com.yaha.nasa_apod.model.view;

import com.yaha.nasa_apod.model.dto.ApodDto;

public record ApodPageView(
        ApodDto apodDto,
        String previousDate,
        String nextDate
) {
}
