package com.example.homelink.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.homelink.service.PantryService;
import com.example.homelink.entity.Pantry;

@RestController
@RequestMapping("/")
public class PantryController {

  @Autowired
  private PantryService pantryService;

  public PantryController(){

  }

  @GetMapping("/pantry")
  public List<Pantry> getAllPantry(){
    try {
      List<Pantry> pantries = pantryService.getAllPantry();
      return pantries;
    } catch (Exception e) {
      throw e;
    }
  }

}
