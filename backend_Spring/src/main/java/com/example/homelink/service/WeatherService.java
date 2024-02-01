package com.example.homelink.service;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.homelink.dto.WeatherDataDTO;
import com.example.homelink.dto.WeatherApiResponseDTO;



@Service
public class WeatherService {
  private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    @Autowired
    public WeatherService(RestTemplate restTemplate,
        @Value("${WEATHER_API_BASE_URL}") String baseUrl,
        @Value("${WEATHER_API_KEY}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

        public WeatherDataDTO getWeather(double latitude, double longitude) {
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl)
            .queryParam("location", latitude + "," + longitude)
            .queryParam("apikey", apiKey)
            .build()
            .toUri();

         WeatherApiResponseDTO.DailyData response = restTemplate.getForObject(url, WeatherApiResponseDTO.DailyData.class);

        
        //conversion

        double maxTempCelsius = response.getTemperatureMax();
        double minTempCelsius = response.getTemperatureMin();

        double maxTempFahrenheit = convertCelsiusToFahrenheit(maxTempCelsius);
        double minTempFahrenheit = convertCelsiusToFahrenheit(minTempCelsius);

        return new WeatherDataDTO(maxTempFahrenheit, minTempFahrenheit);
        // WeatherData is a simple POJO to hold your weather data
    }

    private double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9) / 5 + 32;
    }
}
