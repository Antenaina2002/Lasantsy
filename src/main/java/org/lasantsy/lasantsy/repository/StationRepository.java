package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.models.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StationRepository implements GenericRepository<Station, Long> {

    private final Map<Long, Station> stations = new ConcurrentHashMap<>();

    @Override
    public Station save(Station entity) {
        entity.setId(stations.keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1);
        stations.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Station> findAll() {
        return new ArrayList<>(stations.values());
    }

    @Override
    public Station findById(Long id) {
        return stations.get(id);
    }

    @Override
    public void delete(Station entity) {
        stations.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        stations.remove(id);
    }
}
