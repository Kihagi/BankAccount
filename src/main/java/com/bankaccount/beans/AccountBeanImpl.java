package com.bankaccount.beans;

import com.bankaccount.common.Constants;
import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import com.bankaccount.dao.AccountDAO;
import com.bankaccount.dao.TransactionDAO;
import com.bankaccount.model.Account;
import com.bankaccount.model.BankCustomer;
import com.bankaccount.model.Transaction;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
@Stateless
public class AccountBeanImpl implements AccountBeanI{

    @PersistenceContext(unitName = Constants.MYSQL_PERSISTENCE_UNIT)
    private EntityManager entityManager;
    JLogger jLogger = new JLogger(this.getClass());
    Helper helper = new Helper();

    public BigDecimal getBalance(BankCustomer customer) {
        AccountDAO dao = new AccountDAO(entityManager);
        long id = customer.getId();
        return dao.getBalance(id);
    }

    @Override
    public Account findById(long id) {
        AccountDAO dao = new AccountDAO( entityManager);
        return dao.findById(id);
    }

    @Override
    public  Account findByCustomId(long id) {
        AccountDAO dao = new AccountDAO(entityManager);
        return dao.findByCustomId(id);
    }

    @Override
    public List<Account> findAll() {
        AccountDAO dao = new AccountDAO(entityManager);
        return dao.findAll();
    }


    @Override
    public Account add(Account account) {
        AccountDAO dao = new AccountDAO(entityManager);
        return dao.save(account);
    }

    @Override
    public Account edit(Account account) {
        AccountDAO dao = new AccountDAO(entityManager);
        return dao.merge(account);
    }
}
