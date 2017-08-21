package com.bankaccount.beans;

import com.bankaccount.common.Constants;
import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import com.bankaccount.dao.CustomerDAO;
import com.bankaccount.model.BankCustomer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Local
@Stateless
public class CustomerBeanImpl implements CustomerBeanI {

    @PersistenceContext(unitName = Constants.MYSQL_PERSISTENCE_UNIT)
    private EntityManager entityManager;
    JLogger jLogger = new JLogger(this.getClass());
    Helper helper = new Helper();

    @Override
    public BankCustomer findByUsername(String username) {
        CustomerDAO dao = new CustomerDAO(entityManager);
        return dao.findByUsername(username);
    }

    @Override
    public List<BankCustomer> findAll(String search, int offset, int limit) {
        CustomerDAO dao = new CustomerDAO(entityManager);
        return dao.findAll();
    }

    @Override
    public BankCustomer findById(long id) {
        CustomerDAO dao = new CustomerDAO( entityManager);
        return dao.findById(id);
    }
}
