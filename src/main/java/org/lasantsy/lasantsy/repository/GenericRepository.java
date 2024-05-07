package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.models.Station;

import java.util.List;

public interface GenericRepository<T, R> {

    T save(T entity);

    List<T> findAll();

    T findById(R id);

    void delete(T entity);

    void deleteById(R id);
}
