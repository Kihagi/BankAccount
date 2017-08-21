package com.bankaccount.dao;

import com.bankaccount.common.Constants;
import com.bankaccount.common.JLogger;
import com.bankaccount.model.Transaction;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
public class TransactionDAO extends GenericDAOImpl<Transaction, Long> {

    com.bankaccount.common.JLogger jLogger = new JLogger(this.getClass());

    String deposit = Constants.DEPOSIT;
    String withdraw = Constants.WITHDRAWAL;

    private final EntityManager em;

    public TransactionDAO(EntityManager entityManager)
    {
        super(Transaction.class, entityManager);
        em = entityManager;
    }

    public List<Transaction> fetchAll() {

        List<Transaction> all = em.createQuery("from Transaction t ").setMaxResults(6).getResultList();

        if (all.isEmpty()) {

            return null;

        }  else {

            return all;

        }
    }

    /*
    Fetch Daily Deposits Amount
     */
    public BigDecimal findDaysDepositAmounts(Date date) {

        List amounts = em.createQuery("SELECT t.transaction_amount from Transaction t WHERE t.transaction_date =:date AND " +
                "t.transaction_type=:deposit").setParameter("date", date).setParameter("deposit",deposit).getResultList();

        BigDecimal totalDayTransactions = BigDecimal.ZERO;

        for (final Object amount : amounts) {
            totalDayTransactions = totalDayTransactions.add((BigDecimal) amount);
        }

        if (totalDayTransactions != null) {
            return totalDayTransactions;
        }
        return BigDecimal.ZERO;
    }

    /*
    Fetch Daily Withdrawal Amount
     */
    public BigDecimal findDaysWithdrawalAmounts(Date date) {

        List amounts = em.createQuery("SELECT t.transaction_amount from Transaction t WHERE t.transaction_date =:date AND " +
                "t.transaction_type=:withdraw").setParameter("date", date).setParameter("withdraw",withdraw).getResultList();

        BigDecimal totalDayTransactions = BigDecimal.ZERO;

        for (final Object amount : amounts) {
            totalDayTransactions = totalDayTransactions.add((BigDecimal) amount);
        }

        if (totalDayTransactions != null) {
            return totalDayTransactions;
        }
        return BigDecimal.ZERO;
    }

    /*
    Fetch Daily Deposits Transactions Count
     */
    public int dailyDepositTransactionCount(Date date) {

        List<Transaction> results = em.createQuery("SELECT t FROM Transaction t WHERE  t.transaction_date =:date AND" +
                " t.transaction_type=:deposit").setParameter("date", date).setParameter("deposit",deposit).getResultList();

        int transactionCount = results.size();

        jLogger.i("Deposits Transactions count: " + transactionCount);

        return transactionCount;
    }

    /*
    Fetch Total Amount Transacted
     */
    public BigDecimal findTotalAmountTransacted() {

        List amounts = em.createQuery("SELECT t.transaction_amount from Transaction t ").getResultList();

        BigDecimal totalTransactions = BigDecimal.ZERO;

        for (final Object amount : amounts) {
            totalTransactions = totalTransactions.add((BigDecimal) amount);
        }

        if (totalTransactions != null) {
            return totalTransactions;
        }
        return BigDecimal.ZERO;
    }

    /*
    Fetch Total Amount Deposited
     */
    public BigDecimal findTotalAmountDeposited() {

        List amounts = em.createQuery("SELECT t.transaction_amount from Transaction t WHERE " +
                "t.transaction_type=:deposit ").setParameter("deposit",deposit).getResultList();

        BigDecimal totalDeposits = BigDecimal.ZERO;

        for (final Object amount : amounts) {
            totalDeposits = totalDeposits.add((BigDecimal) amount);
        }

        if (totalDeposits != null) {
            return totalDeposits;
        }
        return BigDecimal.ZERO;
    }

    /*
    Fetch Total Amount Withdrawn
     */
    public BigDecimal findTotalAmountWithdrawn() {

        List amounts = em.createQuery("SELECT t.transaction_amount from Transaction t WHERE " +
                "t.transaction_type=:withdraw ").setParameter("withdraw",withdraw).getResultList();

        BigDecimal totalWithdrawals = BigDecimal.ZERO;

        for (final Object amount : amounts) {
            totalWithdrawals = totalWithdrawals.add((BigDecimal) amount);
        }

        if (totalWithdrawals != null) {
            return totalWithdrawals;
        }
        return BigDecimal.ZERO;
    }

    /*
    Fetch Total Number of Transactions
     */
    public int totalTransactionsNumber() {

        List amount = em.createQuery("SELECT t.transaction_amount from Transaction t ").getResultList();

        BigDecimal totalTransactions = BigDecimal.ZERO;

        int count = amount.size();
        jLogger.i("Total transactions are: " + count);

        if (count >= 0) {
            return count;
        }
        return -1;
    }

    /*
    Fetch Withdrawal Transactions Count
     */
    public int dailyWithdrawalTransactionCount(Date date) {

        List<Transaction> results = em.createQuery("SELECT t FROM Transaction t WHERE  t.transaction_date =:date AND" +
                " t.transaction_type=:withdraw").setParameter("date", date).setParameter("withdraw",withdraw).getResultList();

        int transactionCount = results.size();

        jLogger.i("Withdrawal Transaction count: " + transactionCount);

        return transactionCount;
    }


}
