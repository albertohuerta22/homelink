package com.example.homelink.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.homelink.service.WeatherService;

import java.util.Map;


@RestController
@RequestMapping("/")
public class WeatherController {

    private WeatherService weatherService;
    private  ObjectMapper objectMapper;

    public WeatherController(WeatherService weatherService, ObjectMapper objectMapper) {
        this.weatherService = weatherService;
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/weather")
    public ResponseEntity<Map<String, Object>> getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        String weatherJson = weatherService.getWeatherJson(latitude, longitude);
        Map<String, Object> weatherData;
        try {
            weatherData = objectMapper.readValue(weatherJson, Map.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(weatherData, HttpStatus.OK);
    }
}
