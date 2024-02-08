package com.example.homelink.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.example.homelink.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(WeatherController.class)
@TestPropertySource(properties = "server.port=8082")
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WeatherService weatherService;

    @Nested
    class WeatherTests {

    @SuppressWarnings("null")
    @Test
    void getWeather_Success() throws Exception {
        // Given
        double latitude = 40.712776;
        double longitude = -74.005974;
        Map<String, Object> mockWeatherData = new HashMap<>();
        mockWeatherData.put("maxTempFahrenheit", 70.0);
        mockWeatherData.put("minTempFahrenheit", 50.0);

        given(weatherService.getWeatherJson(latitude, longitude)).willReturn(objectMapper.writeValueAsString(mockWeatherData));

        // When & Then
        mockMvc.perform(get("/weather")
                .param("latitude", String.valueOf(latitude))
                .param("longitude", String.valueOf(longitude))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maxTempFahrenheit").value(70.0))
                .andExpect(jsonPath("$.minTempFahrenheit").value(50.0));
        }
    }

    @SuppressWarnings("null")
    @Test
    void getWeather_InvalidParameters() throws Exception {
        // Given invalid parameters
        String invalidLatitude = "invalid-lat";
        String invalidLongitude = "invalid-long";

        // When & Then
        mockMvc.perform(get("/weather")
                .param("latitude", invalidLatitude)
                .param("longitude", invalidLongitude)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @SuppressWarnings("null")
    @Test
    void getWeather_ServiceThrowsException() throws Exception {
    // Given
    double latitude = 40.712776;
    double longitude = -74.005974;

    willThrow(new RuntimeException("Unexpected error")).given(weatherService).getWeatherJson(latitude, longitude);

    // When & Then
    mockMvc.perform(get("/weather")
            .param("latitude", String.valueOf(latitude))
            .param("longitude", String.valueOf(longitude))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.message").value("Error Occurred")) // Check the message field
            .andExpect(jsonPath("$.details").value("Unexpected error")); // Check the details field
}


}

