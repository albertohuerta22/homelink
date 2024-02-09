package com.example.homelink.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.homelink.entity.Charger;
import com.example.homelink.entity.Shelter;
import com.example.homelink.service.ChargerService;
import com.example.homelink.service.ShelterService;

@RestController
@RequestMapping("/")
public class MapController {

  private final ShelterService shelterService;
  private final ChargerService chargerService;

  public MapController(ShelterService shelterService, ChargerService chargerService){
    this.shelterService = shelterService;
    this.chargerService = chargerService;
  }

  @GetMapping("/map")
  public ResponseEntity<Map<String, Object>> getLocations() {
    List<Shelter> shelters = shelterService.getAllShelters();
    List<Charger> chargers = chargerService.getAllChargers();

    Map<String, Object> locations = new HashMap<>();

    locations.put("shelters", shelters);
    locations.put("chargers", chargers);

    return ResponseEntity.ok(locations);
  }

}
