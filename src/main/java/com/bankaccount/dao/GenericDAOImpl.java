package com.bankaccount.dao;

import com.bankaccount.common.JLogger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
public class GenericDAOImpl<T, PK> implements GenericDAO<T> {

    private final Class<T> entityClass;
    private final EntityManager em;
    private final com.bankaccount.common.JLogger JLogger;

    GenericDAOImpl(Class entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        JLogger = new JLogger(entityClass);
        this.em = entityManager;
    }

    public T findById(long id) {
        try {
            return em.find(entityClass, id);
        } catch(PersistenceException pe) {
            JLogger.e("We found a persistence exception executing findById " + pe.getMessage());
            return null;
        }
    }

    public T find(T entity) {
        try {
            return em.find(entityClass, entity);
        } catch(PersistenceException pe) {
            JLogger.e("We found a persistence exception executing findById " + pe.getMessage());
            return null;
        }
    }

    public T save(T entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing persist" + pe.getMessage());
            return null;
        }
    }

    /*public T update(T entity) {
        try {
            em.merge(entity);
            return entity;
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing persist" + pe.getMessage());
            return null;
        }
    }*/

    public T merge(T entity) {
        try {
            em.merge(entity);
            return entity;
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing merge" + pe.getMessage());
            return null;
        }
    }

    public int count() {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e").getResultList().size();
    }

    public boolean remove(T entity) {
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            //em.remove(entity);
            return true;
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing remove" + pe.getMessage());
            return false;
        }
    }

    public List<T> findAll() {
        try {
            String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return (List<T>) em.createQuery(query).getResultList();
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing findPaged" + pe.getMessage());
            return null;
        }
    }

    public List<T> customQuery(String s, String value) {
        JLogger.i(s + " : " + value);
        try {
            return (List<T>) em.createQuery(s).setParameter("value", value).getResultList();
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing customQuery:" + pe.getMessage());
            return null;
        }
    }

    public List<T> query(String query) {
        try {
            return (List<T>) em.createQuery(query).getResultList();
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing query:" + pe.getMessage());
            return null;
        }
    }

    public List<T> findByObject(String s, Object o) {
        try {
            return (List<T>) em.createQuery(s).setParameter("object", o).getResultList();
        } catch (PersistenceException pe) {
            JLogger.e("We found a persistence exception executing findByObject:" + pe.getMessage());
            return null;
        }
    }
}
