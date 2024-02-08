package com.example.homelink.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;


    @Autowired
    public WeatherService(RestTemplate restTemplate, ObjectMapper objectMapper,
                          @Value("${WEATHER_API_BASE_URL}") String baseUrl,
                          @Value("${WEATHER_API_KEY}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
  
    }

    public String getWeatherJson(double latitude, double longitude) {
        return fetchWeatherData(latitude, longitude);
    }

    private String fetchWeatherData(double latitude, double longitude) {
        @SuppressWarnings("null")
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("location", latitude+ "," + longitude)
                .queryParam("timesteps", "daily")
                .queryParam("apikey", apiKey) // Use the appropriate query parameter for your API key
                .build()
                .toUri();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String rawJson = responseEntity.getBody();

        logger.debug("Weather API JSON response: {}", rawJson);

        return rawJson;
    }
}
