package com.yaha.nasa_apod.clients;

import com.yaha.nasa_apod.model.dto.ApodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class NasaClient {

    private static final String APOD_ENDPOINT = "planetary/apod";

    private final RestClient restClient;
    private final String apiKey;

    public ApodDto fetchApod() {
        return exchangeRequest(ApodDto.class, Map.of());
    }

    public ApodDto fetchApodByDate(String date) {
        return exchangeRequest(ApodDto.class, Map.of("date", date));
    }

    public List<ApodDto> fetchApodsByDateRange(String startDate, String endDate) {
        return exchangeRequest(new ParameterizedTypeReference<>() {
        }, Map.of("start_date", startDate, "end_date", endDate));
    }

    private <T> T exchangeRequest(Class<T> type, Map<String, String> params) {
        return responseSpec(params).body(type);
    }

    private <T> T exchangeRequest(ParameterizedTypeReference<T> responseType, Map<String, String> params) {
        return responseSpec(params).body(responseType);
    }

    private ResponseSpec responseSpec(Map<String, String> params) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromPath(APOD_ENDPOINT).queryParam("api_key", apiKey);

        params.forEach(uriBuilder::queryParam);

        return restClient.get()
                .uri(uriBuilder.build().toUri())
                .retrieve();
    }

}
