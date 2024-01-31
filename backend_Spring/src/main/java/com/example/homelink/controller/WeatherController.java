package com.example.homelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.homelink.dto.WeatherDataDTO;
import com.example.homelink.service.WeatherService;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherDataDTO getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }
}
