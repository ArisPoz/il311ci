package com.cs1200002.apozidis.il311ci.controller;

import com.cs1200002.apozidis.il311ci.model.Location;
import com.cs1200002.apozidis.il311ci.model.Report;
import com.cs1200002.apozidis.il311ci.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/location/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Location> getLocationsById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getLocations(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        List<Location> list = locationService.getLocations(pageNo, pageSize);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/locations")
    public Location addLocation(Location location) {
        return locationService.addLocation(location);
    }
}
