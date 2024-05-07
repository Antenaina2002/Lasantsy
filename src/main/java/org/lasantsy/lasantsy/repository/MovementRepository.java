package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.models.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MovementRepository implements GenericRepository<Movement, Long> {

    private final Map<Long, Movement> movements = new ConcurrentHashMap<>();

    @Override
    public Movement save(Movement entity) {
        entity.setId(movements.keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1);
        movements.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Movement> findAll() {
        return new ArrayList<>(movements.values());
    }

    @Override
    public Movement findById(Long id) {
        return movements.get(id);
    }

    @Override
    public void delete(Movement entity) {
        movements.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        movements.remove(id);
    }
}
