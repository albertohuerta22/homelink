package com.example.homelink.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//my local files
import com.example.homelink.entity.Shelter;
import com.example.homelink.exception.ShelterNotFoundException;
import com.example.homelink.repository.ShelterRepository;

import jakarta.transaction.Transactional;

import com.example.homelink.dto.ShelterDTO;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;
    //GET ALL SHELTERS
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }


    //GET SINGLE SHELTER
    public Optional<Shelter> getShelterById(Long id) {
    System.out.println("Searching for shelter with ID: " + id);
    Optional<Shelter> shelter = shelterRepository.findById(id);
    
    if (shelter.isPresent()) {
        System.out.println("Found shelter: " + shelter.get());
    } else {
        System.out.println("Shelter not found for ID: " + id);
    }
    
        return shelter;
    }

    //CREATE SINGLE
    public Shelter createShelter(ShelterDTO shelterDTO) {
    Shelter newShelter = new Shelter();
    newShelter.setCenterName(shelterDTO.getCenterName());
    newShelter.setBorough(shelterDTO.getBorough());
    newShelter.setAddress(shelterDTO.getAddress());

    // Add any additional logic or validation if needed

    return shelterRepository.save(newShelter);
    }


    //UPDATE SINGLE
     @Transactional
    public Shelter updateShelter(Long id, ShelterDTO shelterDTO) {
    Optional<Shelter> existingShelterOptional = shelterRepository.findById(id);

    if (existingShelterOptional.isPresent()) {
        Shelter existingShelter = existingShelterOptional.get();

        // Add validation, e.g., check if the provided id matches the id in shelterDTO

        // Update fields from DTO
        existingShelter.setCenterName(shelterDTO.getCenterName());
        existingShelter.setBorough(shelterDTO.getBorough());
        existingShelter.setAddress(shelterDTO.getAddress());

        // Save the updated shelter
         return shelterRepository.save(existingShelter);
        } else {
        throw new ShelterNotFoundException("Shelter not found with id: " + id);
    }
    }

    //DELETE SINGLE
    public boolean deleteShelterById(Long id) {
    Optional<Shelter> shelterOptional = shelterRepository.findById(id);
        if (shelterOptional.isPresent()) {
            shelterRepository.delete(shelterOptional.get());
            return true; // Shelter deleted successfully
        } else {
            return false; // Shelter not found
        }
    }
    
}
