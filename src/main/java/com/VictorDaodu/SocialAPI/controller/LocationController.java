package com.VictorDaodu.SocialAPI.controller;


import com.VictorDaodu.SocialAPI.exception.ResourceNotFoundException;
import com.VictorDaodu.SocialAPI.model.Location;

import com.VictorDaodu.SocialAPI.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;


    @GetMapping("/locations")
    public List<Location> getAllLocation() {
        return locationService.getLocationRepository().findAll();
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") Long locationId) throws ResourceNotFoundException {
        Location location =locationService.getLocationRepository().findById(locationId).orElseThrow(() -> new ResourceNotFoundException("location not Found for this id ::" + locationId));
        return ResponseEntity.ok().body(location);
    }

    @PostMapping("/add/location")
    public Location createLocaton(@RequestBody Location location) {
        return locationService.getLocationRepository().save(location);
    }

    @PutMapping("/edit/locatons/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable(value = "id") Long locationId, @RequestBody Location locationDetails)
            throws ResourceNotFoundException {
        Location location=locationService.getLocationRepository().findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id:: " + locationId));
        location.setName(locationDetails.getName());
        return ResponseEntity.ok(locationDetails);
    }


    @DeleteMapping("/locations/{id}")
    public Map<String, Boolean> deleteLocation(@PathVariable(value = "id") Long locationId)
            throws ResourceNotFoundException {
        Location location=locationService.getLocationRepository().findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("location not found for this id :: " + locationId));

        locationService.getLocationRepository().delete(location);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
