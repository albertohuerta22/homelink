package com.example.homelink.controller;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.example.homelink.entity.Pantry;
import com.example.homelink.service.PantryService;

@WebMvcTest(PantryController.class)
@TestPropertySource(properties = "server.port=8082")
public class PantryControllerTest {
  
   @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PantryService pantryService;

    private List<Pantry> pantryList;

    @BeforeEach
    void setUp() {
        this.pantryList = Arrays.asList(
                new Pantry((long) 1, "manhattanPantry", "123FakeSt", "Mike", "mike@food"),
                new Pantry((long) 2, "brooklyn", "brooklynPanry", "Jane", "Jane@food")
        );
    }

    @SuppressWarnings("null")
    @Test
    public void getAllPantry_ShouldReturnPantryList() throws Exception {
        given(pantryService.getAllPantry()).willReturn(pantryList);

        mockMvc.perform(get("/pantry")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(pantryList.size()));
    }

    @SuppressWarnings("null")
    @Test
    public void getAllPantry_ShouldReturnErrorOnException() throws Exception {
        when(pantryService.getAllPantry()).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/pantry")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
    
}
