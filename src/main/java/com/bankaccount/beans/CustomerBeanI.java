package com.bankaccount.beans;

import com.bankaccount.model.BankCustomer;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
public interface CustomerBeanI {

    BankCustomer findByUsername(String username);
    BankCustomer findById(long id);
    List<BankCustomer> findAll(String search, int offset, int limit);
}
