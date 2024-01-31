package com.example.homelink.service;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.homelink.dto.WeatherDataDTO;
import com.example.homelink.dto.WeatherApiResponseDTO;


@Service
public class WeatherService {
  String baseUrl = System.getenv("WEATHER_API_BASE_URL");
  String apiKey = System.getenv("WEATHER_API_KEY");
  String latitude = System.getenv("DEFAULT_LATITUDE");
  String longitude = System.getenv("DEFAULT_LONGITUDE");

    public WeatherDataDTO getWeather(double latitude, double longitude) {
      RestTemplate restTemplate = new RestTemplate();
      URI url = UriComponentsBuilder.fromHttpUrl(baseUrl)
          .queryParam("location", latitude + "," + longitude)
          .queryParam("apikey", apiKey)
          .build()
          .toUri();

        WeatherApiResponseDTO response = restTemplate.getForObject(url, WeatherApiResponseDTO.class);
        // Assume WeatherApiResponse is a class you've created to match the JSON structure of the API response
        System.out.println(response);
        // Conversion logic here
        // double maxTempCelsius = response.getTemperatureMax();
        // double minTempCelsius = response.getTemperatureMin();

        // double maxTempFahrenheit = convertCelsiusToFahrenheit(maxTempCelsius);
        // double minTempFahrenheit = convertCelsiusToFahrenheit(minTempCelsius);

        // return new WeatherDataDTO(maxTempFahrenheit, minTempFahrenheit);
        return new WeatherDataDTO(20, 20);
        // WeatherData is a simple POJO to hold your weather data
    }

    private double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9) / 5 + 32;
    }
}
