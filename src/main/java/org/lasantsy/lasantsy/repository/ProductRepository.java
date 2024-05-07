package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductRepository implements GenericRepository<Product, Long> {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    @Override
    public Product save(Product entity) {
        entity.setId(products.keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1);
        products.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public void delete(Product entity) {
        products.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }
}
