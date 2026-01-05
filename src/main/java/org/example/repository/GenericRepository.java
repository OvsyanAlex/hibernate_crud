package org.example.repository;


import java.util.List;

public interface GenericRepository<T, ID> {
    T save(T entity);

    T update(T entity);

    void deleteById(ID id);

    T getById(ID id);

    List<T> findAll();
}
