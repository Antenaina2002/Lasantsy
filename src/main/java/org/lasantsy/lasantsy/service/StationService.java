package org.lasantsy.lasantsy.service;

import org.lasantsy.lasantsy.models.Station;
import org.lasantsy.lasantsy.repository.StationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station saveStation(Station station) {
        return stationRepository.save(station);
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationById(Long id) {
        return stationRepository.findById(id);
    }

    public void deleteStation(Station station) {
        stationRepository.delete(station);
    }

    public void deleteStationById(Long id) {
        stationRepository.deleteById(id);
    }
}
