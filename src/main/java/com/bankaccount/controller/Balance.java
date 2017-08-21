package com.bankaccount.controller;

import com.bankaccount.beans.AccountBeanI;
import com.bankaccount.beans.CustomerBeanI;
import com.bankaccount.common.JLogger;
import com.bankaccount.model.BankCustomer;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Path("/balance")
public class Balance {

    @EJB
    AccountBeanI accountBeanI;

    @EJB
    CustomerBeanI customerBeanI;

    JLogger jLogger = new JLogger(this.getClass());

    @GET
    @Path("/current")
    @Produces("application/json")
    public Response getBalance() {

        try {

            BankCustomer customer = customerBeanI.findById(1);
            BigDecimal customerBalance = BigDecimal.ZERO;

            try {
                customerBalance = accountBeanI.getBalance(customer);
                jLogger.i("Balance found >>>>>>>>>>>>>>>>>>>> " + customerBalance + " <<<<<<<<<<<<<<<<<<<<");

            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jsob = new JSONObject();
            jsob.put("success", true);
            jsob.put("current balance", customerBalance);

            return Response.status(Response.Status.ACCEPTED.getStatusCode())
                    .entity(jsob.toString()).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
