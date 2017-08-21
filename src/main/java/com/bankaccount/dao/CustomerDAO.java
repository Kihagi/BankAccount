package com.bankaccount.dao;

import com.bankaccount.common.JLogger;
import com.bankaccount.model.BankCustomer;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
public class CustomerDAO extends GenericDAOImpl<BankCustomer, Long> {

    com.bankaccount.common.JLogger jLogger = new JLogger(this.getClass());

    private final EntityManager em;


    public CustomerDAO(EntityManager entityManager)
    {
        super(BankCustomer.class, entityManager);
        em = entityManager;
    }

    public BankCustomer findByUsername(String username)
    {
        // TODO Auto-generated method stub
        List<BankCustomer> results = em.createQuery("SELECT u FROM BankCustomer u WHERE u.username=:username").setParameter("username", username).getResultList();

        if (results.isEmpty()) {

            return null;

        }  else {

            return results.get(0);

        }
    }

    public List<BankCustomer> findAll(String search, int offset, int limit) {
        // TODO Auto-generated method stub
        String query_string;
        if(search != null)
            query_string = "SELECT u FROM BankCustomer u WHERE username like '%" + search + "%' order by u.id asc";
        else
            query_string = "SELECT u FROM BankCustomer u order by u.id asc";
        @SuppressWarnings("unchecked")
        List<BankCustomer> entities = em.createQuery(query_string)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        return entities;
    }
}
