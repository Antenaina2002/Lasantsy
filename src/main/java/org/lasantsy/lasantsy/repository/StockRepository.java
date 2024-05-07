package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.models.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockRepository implements GenericRepository<Stock, Long> {

    private final Map<Long, Stock> stocks = new ConcurrentHashMap<>();

    @Override
    public Stock save(Stock entity) {
        entity.setId(stocks.keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1);
        stocks.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Stock> findAll() {
        return new ArrayList<>(stocks.values());
    }

    @Override
    public Stock findById(Long id) {
        return stocks.get(id);
    }

    @Override
    public void delete(Stock entity) {
        stocks.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        stocks.remove(id);
    }
}
