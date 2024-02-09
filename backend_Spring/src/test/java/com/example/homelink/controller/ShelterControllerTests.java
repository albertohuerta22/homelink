package com.example.homelink.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.homelink.dto.ShelterDTO;
import com.example.homelink.entity.Shelter;
import com.example.homelink.exception.shelter.ShelterNotFoundException;
import com.example.homelink.service.ShelterService;
import com.fasterxml.jackson.databind.ObjectMapper;


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
        Shelter shelter1 = new Shelter("Shelter 1", "Manthattan", "Address1", 40.712776, -74.005974); // Set properties for shelter1 as needed
        Shelter shelter2 = new Shelter("Shelter 2", "Manhattan", "Address2", 40.712776, -74.005974); // Set properties for shelter2 as needed
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
    Shelter expectedShelter = new Shelter("Shelter 1", "Manhattan", "Address1", 40.712776, -74.005974);
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
    Shelter expectedShelter = new Shelter("Shelter 1", "Manhattan", "Address1", 40.712776, -74.005974);
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


    //UPDATE SHELTER
    @SuppressWarnings("null")
    @Test
    void updateShelter() throws Exception {
    // Arrange
    Long shelterId = 1L; // The ID of the shelter to update

    // Original shelter attributes before update
    Shelter originalShelter = new Shelter("Original Shelter Name", "Original Borough", "Address1", 40.712776, -74.005974);
    originalShelter.setId(shelterId); // Assuming the ID is set to simulate the existing shelter

    // New attributes for the update
    ShelterDTO shelterUpdateDTO = new ShelterDTO();
    shelterUpdateDTO.setCenterName("Updated Shelter Name");
    shelterUpdateDTO.setBorough("Updated Borough");
    shelterUpdateDTO.setAddress("Address2"); // Updated address

    // Expected shelter after the update
    Shelter updatedShelter = new Shelter("Updated Shelter Name", "Updated Borough", "Address2", 40.712776, -74.005974);
    updatedShelter.setId(shelterId); // Set the ID to simulate the updated shelter returned by the service

    // Mock the behavior of the shelter service to return the updated shelter when updateShelter is called
    given(shelterService.updateShelter(eq(shelterId), any(ShelterDTO.class))).willReturn(updatedShelter);

    // Convert ShelterDTO to JSON for the request body
    String shelterDTOJson = new ObjectMapper().writeValueAsString(shelterUpdateDTO);

    // Act & Assert
    mockMvc.perform(put("/shelters/{id}", shelterId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(shelterDTOJson))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(shelterId))
            .andExpect(jsonPath("$.centerName").value("Updated Shelter Name"))
            .andExpect(jsonPath("$.borough").value("Updated Borough"))
            .andExpect(jsonPath("$.address").value("Address2")); // Verify the address was updated to "Address2"
    }

    //Delete Shelter 
    @Test
    void deleteShelter_Success() throws Exception {
    Long existingShelterId = 1L;

    // Assuming deleteShelterById returns a boolean
    given(shelterService.deleteShelterById(existingShelterId)).willReturn(true);

    mockMvc.perform(delete("/shelters/{id}", existingShelterId))
            .andExpect(status().isNoContent());

    verify(shelterService).deleteShelterById(existingShelterId);
    }


    @SuppressWarnings("null")
    void deleteShelter_NotFound() throws Exception {
        // Arrange
        Long nonExistentShelterId = 999L; // An ID that does not exist in the database

        // Mock the shelter service to throw ShelterNotFoundException when trying to delete a non-existent shelter
        willThrow(new ShelterNotFoundException("Shelter not found with ID: " + nonExistentShelterId))
                .given(shelterService).deleteShelterById(nonExistentShelterId);

        // Act & Assert
        mockMvc.perform(delete("/shelters/{id}", nonExistentShelterId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // Expecting a 404 Not Found response
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Shelter not found with ID: " + nonExistentShelterId),
                        "The response should contain the error message: Shelter not found with ID: " + nonExistentShelterId));

        // Optionally, verify that the service method was called
        verify(shelterService, times(1)).deleteShelterById(nonExistentShelterId);
    }




}
