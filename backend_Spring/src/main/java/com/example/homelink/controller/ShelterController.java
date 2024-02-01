package com.example.homelink.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.example.homelink.dto.ShelterDTO;
//my local files
import com.example.homelink.entity.Shelter;
import com.example.homelink.exception.shelter.BadRequestException;
import com.example.homelink.exception.shelter.ShelterNotFoundException;

import com.example.homelink.service.ShelterService;

@RestController
@RequestMapping("/shelters")
public class ShelterController {

    private static final Logger logger = LoggerFactory.getLogger(ShelterController.class);


    @Autowired
    private ShelterService shelterService;

    @GetMapping
    public List<Shelter> getAllShelters() {
    List<Shelter> shelters = shelterService.getAllShelters();
        if (shelters.isEmpty()) {
            throw new ShelterNotFoundException("No Available Shelters");
        }
        return shelters;
    }


    //GET SINGLE SHELTER
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
    Shelter shelter = shelterService.getShelterById(id)
        .orElseThrow(() -> new ShelterNotFoundException("Shelter not found with ID: " + id));
    return new ResponseEntity<>(shelter, HttpStatus.OK);
    }

    //CREATE SINGLE SHELTER
    @PostMapping("/create")
    public ResponseEntity<?> createShelter(@RequestBody ShelterDTO shelterDTO) {
    try {
         if (shelterDTO.getCenterName() == null || shelterDTO.getCenterName().trim().isEmpty()) {
            throw new BadRequestException("Center name is required.");
        }

        if (shelterDTO.getAddress() == null || shelterDTO.getAddress().trim().isEmpty()) {
            throw new BadRequestException("Address is required.");
        }
        if (shelterDTO.getBorough() == null || shelterDTO.getBorough().trim().isEmpty()) {
            throw new BadRequestException("Borough is required.");
        }

        // If validation passes, proceed to create the shelter
        Shelter createdShelter = shelterService.createShelter(shelterDTO);
        return new ResponseEntity<>(createdShelter, HttpStatus.CREATED);
   
    } catch (BadRequestException e) {
        // No need to catch BadRequestException here anymore as it's handled globally
        throw e; // Just rethrow it
    } catch (Exception e) {
        // Handle other exceptions
        logger.error("Unexpected error: ", e);
        throw e;
    }
}

    //UPDATE SHELTER
    @PutMapping("/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable Long id, @RequestBody ShelterDTO shelterDTO) {
        try {
            Shelter updatedShelter = shelterService.updateShelter(id, shelterDTO);
            return new ResponseEntity<>(updatedShelter, HttpStatus.OK);
        } catch (ShelterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error updating shelter " +  e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE SHELTER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelter(@PathVariable Long id) {
        boolean deleted = shelterService.deleteShelterById(id);

        if (deleted) {
            String successMessage = "Shelter with ID " + id + " was deleted successfully.";
            logger.info(successMessage); // Add this line for logging
            return new ResponseEntity<>(successMessage, HttpStatus.NO_CONTENT);
        } else {
            String errorMessage = "Shelter not found with ID " + id;
            logger.error(errorMessage); // Add this line for logging
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
  
}
