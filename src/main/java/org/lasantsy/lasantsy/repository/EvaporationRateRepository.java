package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.models.EvaporationRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EvaporationRateRepository implements GenericRepository<EvaporationRate, Long> {

    private final Map<Long, EvaporationRate> evaporationRates = new ConcurrentHashMap<>();

    @Override
    public EvaporationRate save(EvaporationRate entity) {
        entity.setId(evaporationRates.keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1);
        evaporationRates.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<EvaporationRate> findAll() {
        return new ArrayList<>(evaporationRates.values());
    }

    @Override
    public EvaporationRate findById(Long id) {
        return evaporationRates.get(id);
    }

    @Override
    public void delete(EvaporationRate entity) {
        evaporationRates.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        evaporationRates.remove(id);
    }
}
