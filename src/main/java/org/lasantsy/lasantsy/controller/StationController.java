package org.lasantsy.lasantsy.controller;

import org.lasantsy.lasantsy.models.Station;
import org.lasantsy.lasantsy.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping
    public Station createStation(@RequestBody Station station) {
        return stationService.saveStation(station);
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Long id) {
        return stationService.getStationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStationById(@PathVariable Long id) {
        stationService.deleteStationById(id);
    }
}
