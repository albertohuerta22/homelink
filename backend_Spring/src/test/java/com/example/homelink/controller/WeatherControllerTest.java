package com.example.homelink.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.example.homelink.dto.WeatherDataDTO;
import com.example.homelink.service.WeatherService;

@WebMvcTest(WeatherController.class)
@TestPropertySource(properties = "server.port=8082")
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
            WeatherDataDTO mockWeatherData = new WeatherDataDTO(70.0, 50.0); // Example temperatures

            given(weatherService.getWeather(latitude, longitude)).willReturn(mockWeatherData);

            // When & Then
            mockMvc.perform(get("/weather")
                    .param("latitude", String.valueOf(latitude))
                    .param("longitude", String.valueOf(longitude))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.maxTempFahrenheit").value(70.0))
                    .andExpect(jsonPath("$.minTempFahrenheit").value(50.0));
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

            willThrow(new RuntimeException("Unexpected error")).given(weatherService).getWeather(latitude, longitude);

            // When & Then
            mockMvc.perform(get("/weather")
                    .param("latitude", String.valueOf(latitude))
                    .param("longitude", String.valueOf(longitude))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.message").value(containsString("Error Occurred"))); // Adjust based on your global exception handler's response structure
        }
    }
}
