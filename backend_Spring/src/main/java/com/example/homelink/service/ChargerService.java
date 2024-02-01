package com.example.homelink.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//my local files
import com.example.homelink.entity.Charger;
import com.example.homelink.exception.charger.ChargerNotFoundException;
import com.example.homelink.repository.ChargerRepository;

import jakarta.transaction.Transactional;

import com.example.homelink.dto.ChargerDTO;

@Service
public class ChargerService {

    @Autowired
    private ChargerRepository ChargerRepository;
    //GET ALL ChargerS
    public List<Charger> getAllChargers() {
        return ChargerRepository.findAll();
    }


    //GET SINGLE Charger
    public Optional<Charger> getChargerById(Long id) {
    System.out.println("Searching for charger with ID: " + id);
    Optional<Charger> charger = ChargerRepository.findById(id);
    
    if (charger.isPresent()) {
        System.out.println("Found charger: " + charger.get());
    } else {
        System.out.println("Charger not found for ID: " + id);
    }
    
        return charger;
    }

    //CREATE SINGLE
    public Charger createCharger(ChargerDTO chargerDTO) {
    Charger newCharger = new Charger();
    newCharger.setName(chargerDTO.getName());
    newCharger.setLocation(chargerDTO.getLocation());

    // Add any additional logic or validation if needed

    return ChargerRepository.save(newCharger);
    }


    //UPDATE SINGLE
     @Transactional
    public Charger updateCharger(Long id, ChargerDTO chargerDTO) {
    Optional<Charger> existingChargerOptional = ChargerRepository.findById(id);

        if (existingChargerOptional.isPresent()) {
            Charger existingCharger = existingChargerOptional.get();

            // Add validation, e.g., check if the provided id matches the id in chargerDTO

            // Update fields from DTO
            existingCharger.setName(chargerDTO.getName());
            existingCharger.setLocation(chargerDTO.getLocation());

            // Save the updated Charger
            return ChargerRepository.save(existingCharger);
            } else {
            throw new ChargerNotFoundException("Charger not found with id: " + id);
        }
    }

    //DELETE SINGLE
    public boolean deleteChargerById(Long id) {
    Optional<Charger> chargerOptional = ChargerRepository.findById(id);
        if (chargerOptional.isPresent()) {
            ChargerRepository.delete(chargerOptional.get());
            return true; // Charger deleted successfully
        } else {
            return false; // Charger not found
        }
    }
    
}
