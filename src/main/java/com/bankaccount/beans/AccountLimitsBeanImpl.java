package com.bankaccount.beans;

import com.bankaccount.common.Constants;
import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import com.bankaccount.dao.AccountDAO;
import com.bankaccount.dao.AccountLimitsDAO;
import com.bankaccount.model.Account;
import com.bankaccount.model.AccountLimits;
import com.bankaccount.model.BankCustomer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
@Stateless
public class AccountLimitsBeanImpl implements AccountLimitsBeanI {

    @PersistenceContext(unitName = Constants.MYSQL_PERSISTENCE_UNIT)
    private EntityManager entityManager;
    JLogger jLogger = new JLogger(this.getClass());
    Helper helper = new Helper();


    @Override
    public AccountLimits findById(long id) {
        AccountLimitsDAO dao = new AccountLimitsDAO( entityManager);
        return dao.findById(id);
    }

    @Override
    public AccountLimits edit(AccountLimits accountLimits) {
        AccountLimitsDAO dao = new AccountLimitsDAO(entityManager);
        return dao.merge(accountLimits);
    }
}
