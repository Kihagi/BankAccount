package com.bankaccount.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathenge on 8/16/2017.
 */

@Entity
@Table(name = "bank_account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String account_no;
    private BigDecimal account_balance;

    @ManyToOne(optional=true,fetch=FetchType.LAZY)
    private BankCustomer bankCustomer;

    @OneToMany
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public BigDecimal getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(BigDecimal account_balance) {
        this.account_balance = account_balance;
    }

    public BankCustomer getBankCustomer() {
        return bankCustomer;
    }

    public void setBankCustomer(BankCustomer bankCustomer) {
        this.bankCustomer = bankCustomer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account(String account_no, BigDecimal account_balance, BankCustomer bankCustomer, List<Transaction> transactions) {
        super();
        this.account_no = account_no;
        this.account_balance = account_balance;
        this.bankCustomer = bankCustomer;
        this.transactions = transactions;
    }

    public Account() {
        super();
    }
}
