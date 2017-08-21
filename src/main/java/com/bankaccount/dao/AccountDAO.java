package com.bankaccount.dao;

import com.bankaccount.common.JLogger;
import com.bankaccount.model.Account;
import com.bankaccount.model.BankCustomer;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
public class AccountDAO extends GenericDAOImpl<Account, Long> {

    JLogger jLogger = new JLogger(this.getClass());

    private final EntityManager em;
    private Long customerId;

    public AccountDAO(EntityManager entityManager)
    {
        super(BankCustomer.class, entityManager);
        em = entityManager;
    }

    public BigDecimal getBalance(Long customerId) {
        this.customerId = customerId;
        List balance = em.createQuery("SELECT a.account_balance from Account a WHERE a.bankCustomer.id =:customerId").setParameter("customerId", customerId).getResultList();
        jLogger.i("Balance: " + balance.get(0));
        if (balance.get(0) != null) {
            return (BigDecimal) balance.get(0);
        }
        return BigDecimal.ZERO;
    }

    public Account findByCustomId(long accountId) {

        List account = em.createQuery("from Account a WHERE a.id =:accountId").setParameter("accountId", accountId).getResultList();

        if(account==null || account.size()==0){
            return null;
        }else{
            return (Account) account.get(0);
        }

    }
}
