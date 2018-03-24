package com.advisor.service;

import com.advisor.service.Exceptions.DataRepositoryException;

import java.util.List;

public interface IService<T, ID> {

    T create(T t) throws DataRepositoryException;

    void delete(ID id) throws DataRepositoryException;

    List<T> findAll();

    T findById(ID id);

    T update(T t) throws DataRepositoryException;
}
