package com.example.homelink.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


import com.example.homelink.entity.Charger;
import com.example.homelink.service.ChargerService;

@WebMvcTest(ChargerController.class)
@TestPropertySource(properties = "server.port=8082")
class ChargerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChargerService chargerService;

    @Nested
    class GetAllChargersTests {

        @SuppressWarnings("null")
        @Test
        void getAllChargers_Success() throws Exception {
            // Arrange
            Charger charger1 = new Charger("Charger1", "Location1", null);
            Charger charger2 = new Charger("Charger2", "Location2", null);
            List<Charger> allChargers = Arrays.asList(charger1, charger2);
            
            given(chargerService.getAllChargers()).willReturn(allChargers);

            // Act & Assert
            mockMvc.perform(get("/chargers")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(allChargers.size())))
                    .andExpect(jsonPath("$[0].streetAddress", is(charger1.getStreetAddress())))
                    .andExpect(jsonPath("$[1].streetAddress", is(charger2.getStreetAddress())));
        }

        @SuppressWarnings("null")
        @Test
        void getAllChargers_NotFound() throws Exception {
        given(chargerService.getAllChargers()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/chargers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expecting a 200 OK status, empty is acceptable
                .andExpect(jsonPath("$", hasSize(0))); // Expecting an empty JSON array
        }

        @SuppressWarnings("null")
        @Test
        void getAllChargers_ErrorHandling() throws Exception {
            // Arrange
            given(chargerService.getAllChargers()).willThrow(new RuntimeException("Unexpected error"));

            // Act & Assert
            mockMvc.perform(get("/chargers")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().string(containsString("Unexpected error")));
        }
    }

    // Additional nested classes for other tests or test groups
}

