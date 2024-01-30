package com.example.homelink.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.homelink.dto.ShelterDTO;
//my local files
import com.example.homelink.entity.Shelter;
import com.example.homelink.service.ShelterService;

@RestController
@RequestMapping("/shelters")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @GetMapping
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    //GET SINGLE SHELTER
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
    System.out.println("Received ID: " + id);
    Optional<Shelter> shelterOptional = shelterService.getShelterById(id);

        if (shelterOptional.isPresent()) {
            Shelter shelter = shelterOptional.get();
            return new ResponseEntity<>(shelter, HttpStatus.OK);
        } else {
            // Shelter not found, return 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST
    @PostMapping("/create")
    public ResponseEntity<Shelter> createShelter(@RequestBody ShelterDTO shelterDTO) {
        try {
        Shelter createdShelter = shelterService.createShelter(shelterDTO);
        return new ResponseEntity<>(createdShelter, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception and return an appropriate response, e.g., internal server error (500)
            e.printStackTrace();  // Use a logging framework like SLF4J for proper logging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
  
}
