package org.lasantsy.lasantsy.service;

import org.lasantsy.lasantsy.models.EvaporationRate;
import org.lasantsy.lasantsy.repository.EvaporationRateRepository;

import java.util.List;

public class EvaporationRateService {

    private final EvaporationRateRepository evaporationRateRepository;

    public EvaporationRateService(EvaporationRateRepository evaporationRateRepository) {
        this.evaporationRateRepository = evaporationRateRepository;
    }

    public List<EvaporationRate> getAllEvaporationRates() {
        return evaporationRateRepository.findAll();
    }

    public EvaporationRate getEvaporationRateById(Long id) {
        return evaporationRateRepository.findById(id);
    }
}
