package com.bankaccount.dao;

import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
public interface GenericDAO<T> {

    T save(T entity);

    //T merge(T entity);

    T findById(long id);

    T find(T entity);

    boolean remove(T entity);

    List<T> findAll();

    List<T> customQuery(String s, String value);

    List<T> query(String query);

    List<T> findByObject(String s, Object o);

    int count();
}
