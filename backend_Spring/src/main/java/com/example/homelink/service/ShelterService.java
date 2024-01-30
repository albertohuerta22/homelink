package com.example.homelink.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//my local files
import com.example.homelink.entity.Shelter;
import com.example.homelink.repository.ShelterRepository;
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
    newShelter.setName(shelterDTO.getName());
    newShelter.setCapacity(shelterDTO.getCapacity());
    newShelter.setLocation(shelterDTO.getLocation());

    // Add any additional logic or validation if needed

    return shelterRepository.save(newShelter);
}


    //UPDATE SINGLE

    //DELETE SINGLE

    

    // public void deleteShelter(UUID id) {
    //     shelterRepository.deleteById(id);
    // }

    
}
