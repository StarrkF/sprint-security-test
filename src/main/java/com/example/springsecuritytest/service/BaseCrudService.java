package com.example.springsecuritytest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseCrudService<T, ID, R extends JpaRepository<T, ID>> {

    private final R repo;

    public BaseCrudService(R repo) {
        this.repo = repo;
    }

    public final R getRepository() {
        return repo;
    }

    public T findById(ID id) {
        return repo.findById(id).orElse(null);
    }

    public Iterable<T> findAll() {
        return repo.findAll();
    }

    public Iterable<T> findAllById(Iterable<ID> ids) {
        if ( ids == null ) return null;
        return repo.findAllById(ids);
    }

    public Page<T> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public long count() {
        return repo.count();
    }

    public boolean existsById(ID id) {
        return repo.existsById(id);
    }

    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    public void delete(T entity) {
        repo.delete(entity);
    }

    public void deleteAll(Iterable<? extends T> entities) {
        repo.deleteAll(entities);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public T save(T entity) {
        return repo.save(entity);
    }

    public Iterable<T> saveAll(Iterable<T> entities) {
        return repo.saveAll(entities);
    }

}
