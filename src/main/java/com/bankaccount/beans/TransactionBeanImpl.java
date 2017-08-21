package com.bankaccount.beans;

import com.bankaccount.common.Constants;
import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import com.bankaccount.dao.TransactionDAO;
import com.bankaccount.model.Transaction;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
@Stateless
public class TransactionBeanImpl implements TransactionBeanI {

    @PersistenceContext(unitName = Constants.MYSQL_PERSISTENCE_UNIT)
    private EntityManager entityManager;
    JLogger jLogger = new JLogger(this.getClass());
    Helper helper = new Helper();

    /*@Override
    public Transaction findById(long id) {
        TransactionDAO dao = new TransactionDAO( entityManager);
        return dao.findById(id);
    }*/

    @Override
    public Transaction add(Transaction transaction) {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.save(transaction);
    }

    @Override
    public Transaction edit(Transaction transaction) {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.merge(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.findAll();
    }

    @Override
    public List<Transaction> fetchAll() {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.fetchAll();
    }

    @Override
    public BigDecimal fetchDailyDepositAmount(Date date) {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.findDaysDepositAmounts(date);
    }

    @Override
    public BigDecimal fetchDailyWithdrawalAmount(Date date) {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.findDaysWithdrawalAmounts(date);
    }

    @Override
    public boolean delete(Transaction transaction) {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.remove(transaction);
    }

    @Override
    public int dailyDepositTransactionCount(Date date) {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.dailyDepositTransactionCount(date);
    }

    @Override
    public int dailyWithdrawalTransactionCount(Date date) {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.dailyWithdrawalTransactionCount(date);
    }

    @Override
    public BigDecimal fetchTotalAmountTransacted() {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.findTotalAmountTransacted();
    }

    @Override
    public BigDecimal totalAmountDeposited() {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.findTotalAmountDeposited();
    }

    @Override
    public BigDecimal totalAmountWithdrawn() {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.findTotalAmountWithdrawn();
    }

    @Override
    public int totalTransactionsNumber() {
        TransactionDAO dao = new TransactionDAO(entityManager);
        return dao.totalTransactionsNumber();
    }

}
