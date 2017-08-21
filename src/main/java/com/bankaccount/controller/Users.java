package com.bankaccount.controller;

import com.bankaccount.beans.CustomerBeanI;
import com.bankaccount.common.JLogger;
import com.bankaccount.model.BankCustomer;
import org.json.JSONArray;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Mathenge on 8/17/2017.
 */
@Path("/users")
public class Users {

    @EJB
    CustomerBeanI customerBeanI;

    JLogger jLogger = new JLogger(this.getClass());

    private StreamingOutput errorDetails(final String error) {

        return new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException,
                    WebApplicationException {
                try {
                    StringBuffer sb = new StringBuffer(1000);
                    sb.append("{\"success\": false,\"message\":\""+error+"\"");
                    sb.append("}")	;
                    final String jsonString = sb.toString();
                    outputStream.write(jsonString.getBytes());

                } catch (Exception e) {
                    //jLogger.error(e.getMessage(), e);
                    jLogger.e(e.getMessage());
                } finally {
                    try {
                        outputStream.close();
                    } catch (Exception e) {
                    }
                }
            }
        };
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public StreamingOutput getAll() {

        try{

            return  allUsers();

        } catch(Exception e){
            return errorDetails("Something went wrong!");
        }
    }

    private StreamingOutput allUsers() throws Exception {

        return new StreamingOutput() {

            List<BankCustomer> allCustomers = customerBeanI.findAll(null,0,0);

            @Override
            public void write(OutputStream outputStream) throws IOException {

                StringBuffer sb = new StringBuffer(1000);
                sb.append("{\"success\": true,\"rows\":[");
                int size = allCustomers.size();
                int c = 0;

                for (BankCustomer bankCustomer : allCustomers) {
                    c++;
                    if(c == size ){
                        sb.append(bankCustomer.toJson().replaceAll("},","}"));
                    }else{
                        sb.append(bankCustomer.toJson());
                    }
                }
                sb.append("]}")	;
                final String jsonString = sb.toString();
                outputStream.write(jsonString.getBytes());
            }
        };
    }
}
