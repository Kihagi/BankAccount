package com.bankaccount.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Mathenge on 8/16/2017.
 */

@Entity
@Table(name = "bank_transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal transaction_amount;
    private Date transaction_date;
    private String transaction_type;

    @ManyToOne(optional=true,fetch=FetchType.LAZY)
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(BigDecimal transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction(BigDecimal transaction_amount, Date transaction_date, String transaction_type, Account account) {
        super();
        this.transaction_amount = transaction_amount;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.account = account;
    }

    public Transaction() {
        super();
    }
}
