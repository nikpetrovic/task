package com.nikpetrovic.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericRepo<T, ID> implements IGenericRepo<T,ID> {
    @Override
    public T save(T entity) {
        return this.getJpaRepository().save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return this.getJpaRepository().findById(id);
    }

    @Override
    public void delete(T entity) {
        this.getJpaRepository().delete(entity);
    }

    @Override
    public List<T> findAll() {
        return getJpaRepository().findAll();
    }

    @Override
    public List<T> findAllById(List<ID> ids) {
        return getJpaRepository().findAllById(ids);
    }

    protected abstract <R extends JpaRepository<T, ID>> R getJpaRepository();
}
