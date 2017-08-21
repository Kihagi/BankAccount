package com.bankaccount.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Mathenge on 8/16/2017.
 */

@Entity
@Table(name = "account_limits")
public class AccountLimits implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal daily_withdrawal;
    private BigDecimal daily_deposit;
    private BigDecimal withdrawal_transaction;
    private BigDecimal deposit_transaction;
    private int withdrawal_frequency;
    private int deposit_frequency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getDaily_withdrawal() {
        return daily_withdrawal;
    }

    public void setDaily_withdrawal(BigDecimal daily_withdrawal) {
        this.daily_withdrawal = daily_withdrawal;
    }

    public BigDecimal getDaily_deposit() {
        return daily_deposit;
    }

    public void setDaily_deposit(BigDecimal daily_deposit) {
        this.daily_deposit = daily_deposit;
    }

    public BigDecimal getWithdrawal_transaction() {
        return withdrawal_transaction;
    }

    public void setWithdrawal_transaction(BigDecimal withdrawal_transaction) {
        this.withdrawal_transaction = withdrawal_transaction;
    }

    public BigDecimal getDeposit_transaction() {
        return deposit_transaction;
    }

    public void setDeposit_transaction(BigDecimal deposit_transaction) {
        this.deposit_transaction = deposit_transaction;
    }

    public int getWithdrawal_frequency() {
        return withdrawal_frequency;
    }

    public void setWithdrawal_frequency(int withdrawal_frequency) {
        this.withdrawal_frequency = withdrawal_frequency;
    }

    public int getDeposit_frequency() {
        return deposit_frequency;
    }

    public void setDeposit_frequency(int deposit_frequency) {
        this.deposit_frequency = deposit_frequency;
    }

    public AccountLimits(BigDecimal daily_withdrawal, BigDecimal daily_deposit, BigDecimal withdrawal_transaction, BigDecimal deposit_transaction, int withdrawal_frequency, int deposit_frequency) {
        super();
        this.daily_withdrawal = daily_withdrawal;
        this.daily_deposit = daily_deposit;
        this.withdrawal_transaction = withdrawal_transaction;
        this.deposit_transaction = deposit_transaction;
        this.withdrawal_frequency = withdrawal_frequency;
        this.deposit_frequency = deposit_frequency;
    }

    public AccountLimits() {
        super();
    }
}
