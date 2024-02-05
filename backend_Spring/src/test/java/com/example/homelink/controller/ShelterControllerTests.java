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
}
