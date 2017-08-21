package com.bankaccount.controller;

import com.bankaccount.beans.AccountLimitsBeanI;
import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import com.bankaccount.model.AccountLimits;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mathenge on 8/18/2017.
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/dashboard"})
public class Dashboard extends BaseServlet implements Serializable {

    @EJB
    AccountLimitsBeanI accountLimitsBeanI;

    private static final long serialVersionUID = 1L;
    Helper helper = new Helper();
    private final JLogger jLogger = new JLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = this.get(request, "dashboard");
        jLogger.i("Action: " + action);

        switch (action) {
            case "SETTINGS":
                showSettings(request, response);
                break;
        }
    }

    private void showSettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AccountLimits limits = accountLimitsBeanI.findById(1);
        request.setAttribute("limits", limits);

        request.getRequestDispatcher("settings.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = this.get(request, "ACTION");

        switch (action) {

            case "UPDATE_SETTINGS":
                updateSettings(request, response);
                break;
        }

    }

    private void updateSettings(HttpServletRequest request, HttpServletResponse response) {

        AccountLimits limits = accountLimitsBeanI.findById(1);

        String maxDailyDeposit = this.get(request, "maxDailyDeposit");
        String maxDepositTransaction = this.get(request, "maxDepositTransaction");
        String maxDepositFrequency = this.get(request, "maxDepositFrequency");
        String maxDailyWithdrawal = this.get(request, "maxDailyWithdrawal");
        String maxWithdrawalTransaction = this.get(request, "maxWithdrawalTransaction");
        String maxWithdrawalFrequency = this.get(request, "maxWithdrawalFrequency");

        limits.setDaily_deposit(new BigDecimal(maxDailyDeposit));
        limits.setDeposit_transaction(new BigDecimal(maxDepositTransaction));
        limits.setDeposit_frequency(Integer.parseInt(maxDepositFrequency));

        limits.setDaily_withdrawal(new BigDecimal(maxDailyWithdrawal));
        limits.setWithdrawal_transaction(new BigDecimal(maxWithdrawalTransaction));
        limits.setWithdrawal_frequency(Integer.parseInt(maxWithdrawalFrequency));

        if (accountLimitsBeanI.edit(limits) != null) {
            this.respond(response, true, " Account Limits successfully saved", null);
        } else
            this.respond(response, true, "Account Limits could not be saved", null);
    }
}
