package com.yaha.nasa_apod.clients;

import com.yaha.nasa_apod.model.dto.ApodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NasaClient {

    private static final String APOD_ENDPOINT = "planetary/apod";

    private final RestClient restClient;

    @Value("${api.nasa.key}")
    private String apiKey;

    public ApodDto fetchApod() {
        return fetchNasa(ApodDto.class, new HashMap<>());
    }

    public ApodDto fetchApodByDate(String date) {
        return fetchNasa(ApodDto.class, Map.of("date", date));
    }

    public List<ApodDto> fetchApodsByDateRange(String startDate, String endDate) {
        return fetchNasa(new ParameterizedTypeReference<>() {
        }, Map.of("start_date", startDate, "end_date", endDate));
    }

    private <T> T fetchNasa(Class<T> clazz, Map<String, String> params) {
        UriBuilder uriBuilder = UriComponentsBuilder.fromPath(APOD_ENDPOINT).queryParam("api_key", apiKey);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriBuilder = uriBuilder.queryParam(entry.getKey(), entry.getValue());
        }

        return restClient.get()
                .uri(uriBuilder.build())
                .retrieve()
                .body(clazz);

    }

    private <T> T fetchNasa(ParameterizedTypeReference<T> responseType, Map<String, String> params) {
        UriBuilder uriBuilder = UriComponentsBuilder.fromPath(APOD_ENDPOINT).queryParam("api_key", apiKey);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriBuilder = uriBuilder.queryParam(entry.getKey(), entry.getValue());
        }

        return restClient.get()
                .uri(uriBuilder.build())
                .retrieve()
                .body(responseType);

    }

}
