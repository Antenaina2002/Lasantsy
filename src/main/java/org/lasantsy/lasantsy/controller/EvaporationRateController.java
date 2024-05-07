package org.lasantsy.lasantsy.controller;

import org.lasantsy.lasantsy.models.EvaporationRate;
import org.lasantsy.lasantsy.service.EvaporationRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaporationRates")
public class EvaporationRateController {

    private final EvaporationRateService evaporationRateService;

    @Autowired
    public EvaporationRateController(EvaporationRateService evaporationRateService) {
        this.evaporationRateService = evaporationRateService;
    }

    @GetMapping
    public List<EvaporationRate> getAllEvaporationRates() {
        return evaporationRateService.getAllEvaporationRates();
    }

    @GetMapping("/{id}")
    public EvaporationRate getEvaporationRateById(@PathVariable Long id) {
        return evaporationRateService.getEvaporationRateById(id);
    }
}
