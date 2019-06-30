package com.nikpetrovic.task.repository;

import java.util.List;
import java.util.Optional;

public interface IGenericRepo<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    void delete(T entity);
    List<T> findAll();
    List<T> findAllById(List<ID> ids);
}
