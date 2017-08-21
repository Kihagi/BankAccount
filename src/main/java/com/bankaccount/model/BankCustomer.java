package com.bankaccount.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathenge on 8/16/2017.
 */

@Entity
@Table(name = "bank_customer")
public class BankCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @OneToMany
    private List<Account> accounts = new ArrayList<Account>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String toJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("{\"id\":").append("\"").append(getId()).append("\",");
        sb.append("\"username\":\"").append(getUsername() == null? "" : getUsername()).append("\",").append("\"},");
        sb.append("\n");
        return sb.toString();
    }

    public BankCustomer(String username, List<Account> accounts) {
        super();
        this.username = username ;
        this.accounts = accounts;
    }

    public BankCustomer() {
        super();
    }
}
