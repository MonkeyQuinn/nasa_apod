package com.yaha.nasa_apod.configurations;

import com.deepl.api.DeepLClient;
import com.yaha.nasa_apod.clients.NasaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiClientConfig {

    @Bean
    public RestClient restClient(@Value("${api.nasa.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public NasaClient nasaClient(RestClient restClient, @Value("${api.nasa.key}") String apiKey) {
        return new NasaClient(restClient, apiKey);
    }

    @Bean
    public DeepLClient deepLClient(@Value("${api.deepl.key}") String apiKey) {
        return new DeepLClient(apiKey);
    }

}
