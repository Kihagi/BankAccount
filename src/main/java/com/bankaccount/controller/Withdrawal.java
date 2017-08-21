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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Mathenge on 8/18/2017.
 */
@Path("/withdraw")
public class Withdrawal {

    JLogger jLogger = new JLogger(this.getClass());
    Helper helper = new Helper();

    @EJB
    TransactionBeanI transactionBeanI;

    @EJB
    AccountLimitsBeanI accountLimitsBeanI;

    @EJB
    AccountBeanI accountBeanI;

    @GET
    @Produces("application/json")
    @Path("amount/{amount}")
    public Response withdrawCash(@PathParam("amount") BigDecimal amount)  {

        try {

            JSONObject jsob = new JSONObject();

            jLogger.i("Amount to be withdrawn: " + amount);

            Transaction transaction = new Transaction();

            AccountLimits limits = accountLimitsBeanI.findById(1);

            /*
            Get Daily Max Withdrawal Limit
             */
            BigDecimal dailyWithdrawalLimit = limits.getDaily_withdrawal();
            jLogger.i("dailyWithdrawalLimit: " + dailyWithdrawalLimit);

             /*
             Get Max Withdrawal Per Transaction
             */
            BigDecimal maxPerTransaction = limits.getWithdrawal_transaction();
            jLogger.i("Max Per Transaction: " + maxPerTransaction);

            /*
             Get Daily Max Withdrawal Frequency
             */
            int withdrawalFrequency = limits.getWithdrawal_frequency();
            jLogger.i("Withdrawal Freq: " + withdrawalFrequency + " / day");

            try {

                Account account = accountBeanI.findByCustomId(1);
                BigDecimal balance = account.getAccount_balance();

                BigDecimal daysTransactionAmount = null;
                int withdrawalsCount = 0;

                try {

                    Date checkDate = helper.formatDate(new Date());
                    daysTransactionAmount = transactionBeanI.fetchDailyWithdrawalAmount(checkDate);
                    jLogger.i("Amount imekam: " + daysTransactionAmount);

                    withdrawalsCount = transactionBeanI.dailyWithdrawalTransactionCount(checkDate);
                    jLogger.i("Depo count: " + withdrawalsCount);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (amount != null ) {

                    if (daysTransactionAmount.add(amount).longValue() <= dailyWithdrawalLimit.longValue()) {

                        if (amount.longValue() <= maxPerTransaction.longValue()) {

                            if (withdrawalsCount < withdrawalFrequency) {

                                transaction.setTransaction_amount(amount);
                                transaction.setTransaction_type(Constants.WITHDRAWAL);
                                transaction.setAccount(account);
                                Date persistDate = helper.formatDate(new Date());
                                transaction.setTransaction_date(persistDate);
                                transactionBeanI.add(transaction);

                                jLogger.i("Balance currently: " + balance);
                                balance = balance.subtract(amount);
                                jLogger.i("Balance after depo: " + balance);
                                account.setAccount_balance(balance);
                                accountBeanI.edit(account);

                                jsob.put("success", true);
                                jsob.put("message", "Successfully withdrawn "+ amount + "");

                            } else {
                                jsob.put("success", false);
                                jsob.put("message", "Exceeded Maximum Withdrawal Frequency");
                            }


                        } else {
                            jsob.put("success", false);
                            jsob.put("message", "Exceeded Maximum Withdrawal Per Transaction");
                        }
                    } else {
                        jsob.put("success", false);
                        jsob.put("message", "Exceeded Maximum Withdrawal Deposit");
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
