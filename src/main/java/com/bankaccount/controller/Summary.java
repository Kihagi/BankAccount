package com.bankaccount.controller;

import com.bankaccount.beans.TransactionBeanI;
import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import com.bankaccount.model.Transaction;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mathenge on 8/16/2017.
 */
@WebServlet(name = "Summary", urlPatterns = {"/summary"})
public class Summary extends BaseServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    Helper helper = new Helper();
    private final JLogger jLogger = new JLogger(this.getClass());

    @EJB
    TransactionBeanI transactionBeanI;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
        Get Total Amount Transacted
         */
        BigDecimal totalTransactedAmount = transactionBeanI.fetchTotalAmountTransacted();
        jLogger.i("Total Amount Transacted: " + totalTransactedAmount);
        request.setAttribute("totalTransacted", totalTransactedAmount);

        /*
        Get Total Deposits
         */
        BigDecimal totalDeposits = transactionBeanI.totalAmountDeposited();
        jLogger.i("Total Amount Deposited: " + totalTransactedAmount);
        request.setAttribute("totalDeposits", totalDeposits);

        /*
        Get Total Withdrawals
         */
        BigDecimal totalWithdrawals = transactionBeanI.totalAmountWithdrawn();
        jLogger.i("Total Amount Withdrawn: " + totalWithdrawals);
        request.setAttribute("totalWithdrawals", totalWithdrawals);

        /*
        Get Total Number of Transactions
         */
        int totalTransactionsCount = transactionBeanI.totalTransactionsNumber();
        jLogger.i("Total Number of Transactions: " + totalTransactionsCount);
        request.setAttribute("totalTransactionCount", totalTransactionsCount);

        List<Transaction> transactions = transactionBeanI.fetchAll();
        jLogger.i("Transactions size " + transactions.size());
        request.setAttribute("transactions", transactions);

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
