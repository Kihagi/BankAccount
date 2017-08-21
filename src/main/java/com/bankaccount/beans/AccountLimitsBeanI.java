package com.bankaccount.beans;

import com.bankaccount.model.AccountLimits;

import javax.ejb.Local;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
public interface AccountLimitsBeanI {

    AccountLimits findById(long id);
    AccountLimits edit(AccountLimits accountLimits);

}
