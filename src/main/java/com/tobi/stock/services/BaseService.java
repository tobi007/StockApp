package com.tobi.stock.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 6:42 PM
 **/

public abstract class BaseService<T, ID extends Serializable> {
    protected JpaRepository<T, ID> repository;

    public void setRepository(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T save(T t) {
        return this.repository.save(t);
    }

    public List<T> save(Iterable<T> t) {
        return this.repository.saveAll(t);
    }

    public T find(ID id) {
        return this.repository.getOne(id);
    }

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public Page<T> findAllPaged(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public void delete(T t) {
        this.repository.delete(t);
    }

    public void delete(ID id) {
        this.repository.deleteById(id);
    }
}
