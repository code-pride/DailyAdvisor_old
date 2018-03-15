package com.advisor.service;

import com.advisor.service.Exceptions.DataRepositoryException;

import java.util.List;

public interface IService<T, ID> {

    public T create(T t) throws DataRepositoryException;

    public void delete(ID id) throws DataRepositoryException;

    public List<T> findAll();

    public T findById(ID id);

    public T update(T t) throws DataRepositoryException;
}
