package com.example.homelink.controller;

import java.util.List;
import java.util.Optional;

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

//my local files
import com.example.homelink.entity.Charger;
import com.example.homelink.exception.charger.ChargerNotFoundException;
import com.example.homelink.exception.shelter.BadRequestException;
import com.example.homelink.exception.shelter.ShelterNotFoundException;
import com.example.homelink.service.ChargerService;

import jakarta.validation.Valid;

import com.example.homelink.dto.ChargerDTO;

@RestController
@RequestMapping("/chargers")
public class ChargerController {

    private static final Logger logger = LoggerFactory.getLogger(ChargerController.class);


    @Autowired
    private ChargerService ChargerService;

    @GetMapping
    public List<Charger> getAllChargers() {
        List<Charger> chargers = ChargerService.getAllChargers();
        if (chargers.isEmpty()) {
            throw new ShelterNotFoundException("No Available Chargers");
        }
        return chargers;
    }
    

    //GET SINGLE CHARGER
    @GetMapping("/{id}")
    public ResponseEntity<Charger> getChargerById(@PathVariable Long id) {
    System.out.println("Received ID: " + id);
    Optional<Charger> chargerOptional = ChargerService.getChargerById(id);

        if (chargerOptional.isPresent()) {
            Charger charger = chargerOptional.get();
            return new ResponseEntity<>(charger, HttpStatus.OK);
        } else {
            // Charger not found, return 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //CREATE SINGLE CHARGER
    @SuppressWarnings("null")
    @PostMapping("/create")
    public ResponseEntity<Charger> createCharger(@Valid @RequestBody ChargerDTO chargerDTO) {
    // Validate the ChargerDTO fields manually
    if (chargerDTO.getStreetAddress() == null || chargerDTO.getStreetAddress().trim().isEmpty()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Name is required
    }
    // You can add more validation checks for other fields here

    try {
        Charger createdCharger = ChargerService.createCharger(chargerDTO);
        return new ResponseEntity<>(createdCharger, HttpStatus.CREATED);
    } catch (BadRequestException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        logger.error("Unexpected error: ", e);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    //UPDATE CHARGER
    @PutMapping("/{id}")
    public ResponseEntity<Charger> updateCharger(@PathVariable Long id, @RequestBody ChargerDTO chargerDTO) {
        try {
            Charger updatedCharger = ChargerService.updateCharger(id, chargerDTO);
            return new ResponseEntity<>(updatedCharger, HttpStatus.OK);
        } catch (ChargerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error updating charger " +  e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE CHARGER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharger(@PathVariable Long id) {
        boolean deleted = ChargerService.deleteChargerById(id);

        if (deleted) {
            String successMessage = "Charger with ID " + id + " was deleted successfully.";
            logger.info(successMessage); // Add this line for logging
            return new ResponseEntity<>(successMessage, HttpStatus.NO_CONTENT);
        } else {
            String errorMessage = "Charger not found with ID " + id;
            logger.error(errorMessage); // Add this line for logging
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
}
