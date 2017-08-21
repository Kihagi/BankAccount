package com.bankaccount.controller;

import com.bankaccount.beans.AccountBeanI;
import com.bankaccount.beans.AccountLimitsBeanI;
import com.bankaccount.beans.TransactionBeanI;
import com.bankaccount.common.Constants;
import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import com.bankaccount.model.Account;
import com.bankaccount.model.AccountLimits;
import com.bankaccount.model.Transaction;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Path("/deposit")
public class Deposit {

    @EJB
    TransactionBeanI transactionBeanI;

    @EJB
    AccountBeanI accountBeanI;

    @EJB
    AccountLimitsBeanI accountLimitsBeanI;

    JLogger jLogger = new JLogger(this.getClass());
    Helper helper = new Helper();

    @GET
    @Produces("application/json")
    @Path("amount/{amount}")
    public Response depositCash(@PathParam("amount") BigDecimal amount)  {

        try {

            JSONObject jsob = new JSONObject();

            jLogger.i("Amount to be deposited: " + amount);

            Transaction transaction = new Transaction();

            AccountLimits limits = accountLimitsBeanI.findById(1);

            /*
            Get Daily Max Deposit
             */
            BigDecimal dailyDepositLimit = limits.getDaily_deposit();
            jLogger.i("dailyDepositLimit: " + dailyDepositLimit);

             /*
             Get Max Deposit Per Transaction
             */
            BigDecimal maxPerTransaction = limits.getDeposit_transaction();
            jLogger.i("Max Per Transaction: " + maxPerTransaction);

            /*
             Get Daily Max Deposit Frequency
             */
            int depositFrequency = limits.getDeposit_frequency();
            jLogger.i("Deposit Freq: " + depositFrequency + " / day");

            try {
                Account account = accountBeanI.findByCustomId(1);
                BigDecimal balance = account.getAccount_balance();

                BigDecimal daysTransactionAmount = null;
                int depositsCount = 0;

                try {

                    Date checkDate = helper.formatDate(new Date());
                    daysTransactionAmount = transactionBeanI.fetchDailyDepositAmount(checkDate);
                    jLogger.i("Amount imekam: " + daysTransactionAmount);

                    depositsCount = transactionBeanI.dailyDepositTransactionCount(checkDate);
                    jLogger.i("Depo count: " + depositsCount);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (amount != null ) {

                    if (daysTransactionAmount.add(amount).longValue() <= dailyDepositLimit.longValue()) {

                        if (amount.longValue() <= maxPerTransaction.longValue()) {

                            if (depositsCount < depositFrequency) {

                                transaction.setTransaction_amount(amount);
                                transaction.setTransaction_type(Constants.DEPOSIT);
                                transaction.setAccount(account);
                                Date persistDate = helper.formatDate(new Date());
                                transaction.setTransaction_date(persistDate);
                                transactionBeanI.add(transaction);

                                jLogger.i("Balance currently: " + balance);
                                balance = balance.add(amount);
                                jLogger.i("Balance after depo: " + balance);
                                account.setAccount_balance(balance);
                                accountBeanI.edit(account);

                                jsob.put("success", true);
                                jsob.put("message", "Successfully deposited "+ amount + "");

                            } else {

                                jsob.put("success", false);
                                jsob.put("message", "Exceeded Maximum Deposit Frequency");
                            }


                        } else {
                            jsob.put("success", false);
                            jsob.put("message", "Exceeded Maximum Deposit Per Transaction");
                        }
                    } else {

                        jsob.put("success", false);
                        jsob.put("message", "Exceeded Maximum Daily Deposit");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return Response.status(Response.Status.ACCEPTED.getStatusCode())
                    .entity(jsob.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

}
