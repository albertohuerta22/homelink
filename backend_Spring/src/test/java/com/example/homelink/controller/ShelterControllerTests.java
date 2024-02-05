package com.example.homelink.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import com.example.homelink.entity.Shelter;
import com.example.homelink.service.ShelterService;
@WebMvcTest(ShelterController.class)
@TestPropertySource(properties = "server.port=8082")
public class ShelterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterService shelterService;


    //GET ALL SHELTERTS
    @SuppressWarnings("null")
    @Test
    void getAllShelters() throws Exception {
        // Arrange
        Shelter shelter1 = new Shelter("Shelter 1", "Manthattan", "Address1"); // Set properties for shelter1 as needed
        Shelter shelter2 = new Shelter("Shelter 2", "Manhattan", "Address2"); // Set properties for shelter2 as needed
        List<Shelter> allShelters = Arrays.asList(shelter1, shelter2);
        
        given(shelterService.getAllShelters()).willReturn(allShelters);

        // Act & Assert
        mockMvc.perform(get("/shelters") // Replace "/shelters" with your actual endpoint
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(allShelters.size()));
                // You can add more expect() statements to verify the details of the shelters
    }

    
    //GET SINGLE SHELTER
    @SuppressWarnings("null")
    @Test
    void getShelterById() throws Exception {
    // Arrange
    Long shelterId = 1L; // Simulated ID that would be auto-generated
    Shelter expectedShelter = new Shelter("Shelter 1", "Manhattan", "Address1");
    // Assuming the ID is set internally, e.g., by the service layer or the database

    given(shelterService.getShelterById(shelterId)).willReturn(Optional.of(expectedShelter));

    // Act & Assert
    mockMvc.perform(get("/shelters/{id}", shelterId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.centerName").value("Shelter 1"))
            .andExpect(jsonPath("$.borough").value("Manhattan"))
            .andExpect(jsonPath("$.address").value("Address1"));
    // Omit the ID check in the response if the client isn't supposed to set it or if it's not relevant to what you're testing
    }

    //CREATE SINGLE SHELTER
    @SuppressWarnings("null")
    @Test
    void createShelter() throws Exception {
    // Arrange
    Long shelterId = 1L; // Simulated ID that would be auto-generated
    Shelter expectedShelter = new Shelter("Shelter 1", "Manhattan", "Address1");
    // Assuming the ID is set internally, e.g., by the service layer or the database

    given(shelterService.getShelterById(shelterId)).willReturn(Optional.of(expectedShelter));

    // Act & Assert
    mockMvc.perform(get("/shelters/{id}", shelterId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.centerName").value("Shelter 1"))
            .andExpect(jsonPath("$.borough").value("Manhattan"))
            .andExpect(jsonPath("$.address").value("Address1"));
    // Omit the ID check in the response if the client isn't supposed to set it or if it's not relevant to what you're testing
    }


}
