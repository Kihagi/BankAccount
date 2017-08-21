package com.bankaccount.beans;

import com.bankaccount.model.Transaction;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
public interface TransactionBeanI {

    Transaction add(Transaction transaction);
    Transaction edit(Transaction transaction);
    List<Transaction> findAll();
    List<Transaction> fetchAll();
    int dailyDepositTransactionCount(Date date);
    int dailyWithdrawalTransactionCount(Date date);
    BigDecimal fetchDailyDepositAmount(Date date);
    BigDecimal fetchTotalAmountTransacted();
    BigDecimal totalAmountDeposited();
    BigDecimal totalAmountWithdrawn();
    int totalTransactionsNumber();
    BigDecimal fetchDailyWithdrawalAmount(Date date);
    boolean delete(Transaction transaction);
   //Transaction findById(long id);
}
