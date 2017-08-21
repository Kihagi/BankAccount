package com.bankaccount.dao;

import com.bankaccount.common.JLogger;
import com.bankaccount.model.AccountLimits;
import com.bankaccount.model.BankCustomer;

import javax.persistence.EntityManager;

/**
 * Created by Mathenge on 8/17/2017.
 */
public class AccountLimitsDAO extends GenericDAOImpl<AccountLimits, Long>  {

    com.bankaccount.common.JLogger jLogger = new JLogger(this.getClass());

    private final EntityManager em;

    public AccountLimitsDAO(EntityManager entityManager)
    {
        super(AccountLimits.class, entityManager);
        em = entityManager;
    }

}
