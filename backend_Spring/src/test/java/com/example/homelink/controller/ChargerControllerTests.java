package com.example.homelink.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import com.example.homelink.dto.ChargerDTO;
import com.example.homelink.entity.Charger;
import com.example.homelink.service.ChargerService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        Charger charger1 = new Charger("Charger1", "Location1", 40.712776, -74.005974);
        Charger charger2 = new Charger("Charger2", "Location 1", 40.712776, -74.005974);
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

    @Nested
    class GetChargerByIdTests {

        @SuppressWarnings("null")
        @Test
        void getChargerById_Success() throws Exception {
            Long chargerId = 1L;
            Charger charger = new Charger("Charger1", "Location1", 40.712776, -74.005974);
            charger.setId(chargerId);

            given(chargerService.getChargerById(chargerId)).willReturn(Optional.of(charger));

            mockMvc.perform(get("/chargers/{id}", chargerId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(chargerId.intValue())))
                    .andExpect(jsonPath("$.streetAddress", is(charger.getStreetAddress())));
        }

        @SuppressWarnings("null")
        @Test
        void getChargerById_NotFound() throws Exception {
            Long nonExistentChargerId = 999L;

            given(chargerService.getChargerById(nonExistentChargerId)).willReturn(Optional.empty());

            mockMvc.perform(get("/chargers/{id}", nonExistentChargerId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        @SuppressWarnings("null")
        @Test
        void getChargerById_ErrorHandling() throws Exception {
            Long chargerId = 1L;

            given(chargerService.getChargerById(chargerId)).willThrow(new RuntimeException("Unexpected error"));

            mockMvc.perform(get("/chargers/{id}", chargerId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().string(containsString("Unexpected error")));
        }
    }


    @Nested
    class CreateChargerTests {

        @SuppressWarnings("null")
        @Test
        void createCharger_Success() throws Exception {
            ChargerDTO chargerDTO = new ChargerDTO("streetAddress1", "Location", 40.712776, -74.005974);
            Charger createdCharger = new Charger("Charger Name", "Location", 40.712776, -74.005974);
            createdCharger.setId(1L); // Assuming IDs are generated upon creation

            given(chargerService.createCharger(any(ChargerDTO.class))).willReturn(createdCharger);

            mockMvc.perform(post("/chargers/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(chargerDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(createdCharger.getId().intValue())))
                    .andExpect(jsonPath("$.streetAddress", is(createdCharger.getStreetAddress())));
        }

        @SuppressWarnings("null")
        @Test
        void createCharger_ValidationFailed() throws Exception {
            ChargerDTO invalidChargerDTO = new ChargerDTO( "", "", 0, 0); // Example of invalid DTO

            mockMvc.perform(post("/chargers/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(invalidChargerDTO)))
                    .andExpect(status().isBadRequest());
            // You can add more assertions here to check for specific validation error messages if your API provides them
        }

        @SuppressWarnings("null")
        @Test
        void createCharger_ErrorHandling() throws Exception {
            ChargerDTO chargerDTO = new ChargerDTO("Charger Name", "Location", 40.712776, -74.005974);

            given(chargerService.createCharger(any(ChargerDTO.class))).willThrow(new RuntimeException("Unexpected error"));

            mockMvc.perform(post("/chargers/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(chargerDTO)))
                    .andExpect(status().isInternalServerError()); //may have to adjust for a message
        }
    }

  
}

