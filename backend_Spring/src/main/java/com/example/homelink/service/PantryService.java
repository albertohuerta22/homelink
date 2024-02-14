package com.example.homelink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homelink.repository.PantryRepository;
import com.example.homelink.entity.Pantry;

@Service
public class PantryService {
  
  public PantryService(){}

  @Autowired
  private PantryRepository pantryRepository;

  public List<Pantry> getAllPantry(){
    return pantryRepository.findAll();
  }

  public String getPantry(){
    return "";
  }

}
