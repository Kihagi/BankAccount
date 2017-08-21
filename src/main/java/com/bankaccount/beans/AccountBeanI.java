package com.bankaccount.beans;

import com.bankaccount.model.Account;
import com.bankaccount.model.BankCustomer;
import com.bankaccount.model.Transaction;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
public interface AccountBeanI {

    public BigDecimal getBalance(BankCustomer customer);
    Account findById(long id);
    Account findByCustomId(long id);
    List<Account> findAll();
    Account add(Account account);
    Account edit(Account account);
}
