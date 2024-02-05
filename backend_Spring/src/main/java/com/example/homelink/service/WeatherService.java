package com.example.homelink.service;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.homelink.dto.WeatherDataDTO;
import com.example.homelink.dto.WeatherApiResponseDTO;

//caching imports
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;


@Service
public class WeatherService {
  
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;
    private final Cache<String, WeatherDataDTO> cache;

    @Autowired
    public WeatherService(RestTemplate restTemplate,
    @Value("${WEATHER_API_BASE_URL}") String baseUrl,
    @Value("${WEATHER_API_KEY}") String apiKey) 
    {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
    this.apiKey = apiKey;
    this.cache = Caffeine.newBuilder()
      .maximumSize(100) // Adjust the cache size as needed
      .expireAfterWrite(30, TimeUnit.MINUTES) // Cache expires after 30 minutes
      .build();
    }

    public WeatherDataDTO getWeather(double latitude, double longitude) {
    String cacheKey = latitude + "," + longitude;
    WeatherDataDTO cachedData = cache.getIfPresent(cacheKey);


    if (cachedData != null) {
            return cachedData;
    } else {
        @SuppressWarnings("null")
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl)
      .queryParam("location", latitude + "," + longitude)
      .queryParam("apikey", apiKey)
      .build()
      .toUri();

          
      
      WeatherApiResponseDTO.DailyData response = restTemplate.getForObject(url, WeatherApiResponseDTO.DailyData.class);
      
      
      @SuppressWarnings("null")
      //conversion to farenheit
      double minTempCelsius = response.getTemperatureMin();
      double maxTempCelsius = response.getTemperatureMax();

      double maxTempFahrenheit = convertCelsiusToFahrenheit(maxTempCelsius);
      double minTempFahrenheit = convertCelsiusToFahrenheit(minTempCelsius);

      WeatherDataDTO weatherData = new WeatherDataDTO(maxTempFahrenheit, minTempFahrenheit);
      // WeatherData is a simple POJO to hold your weather data

      // Store data in cache
      cache.put(cacheKey, weatherData);

      return weatherData;
      }
    }
    

    private double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9) / 5 + 32;
    }
}
