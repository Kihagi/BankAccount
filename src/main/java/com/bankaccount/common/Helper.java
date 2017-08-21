package com.bankaccount.common;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by Mathenge on 8/16/2017.
 */
public class Helper {

    private static final String MMM_d_yyyy = "MMM d, yyyy";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String ROWS = "rows";
    private static final String BOTH = "BOTH";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String HTTP_POST = "POST";
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String HTTP_GET = "GET";
    private static final String PASSWORD = "password";

    private final JLogger jLogger = new JLogger(this.getClass());
    public static final long serialVersionUID = 1L;

    public long toLong(Object o)
    {
        try {
            return Long.valueOf(o.toString());
        } catch (NullPointerException npe) {
            return 0;
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public String toString(Object o)
    {
        try {
            return o.toString();
        } catch (NullPointerException npe) {
            return "";
        }
    }

    public Date formatDate(Date date) {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date dateWithZeroTime = null;
        try {
            dateWithZeroTime = formatter.parse(formatter.format(date));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateWithZeroTime;
    }


    public String result(boolean status, String message, JSONObject json)
    {
        JSONObject obj = new JSONObject();
        try {
            obj.put(Helper.SUCCESS, status);
            obj.put(Helper.MESSAGE, message);
            obj.put("data", json != null ? json.toString() : json);
            return obj.toString();
        } catch (JSONException je) {
            return null;
        }
    }

    public JSONObject response(boolean status, String message)
    {
        try {
            return new JSONObject().put("success", status).put("message", message);
        } catch (JSONException je) {
            return null;
        }
    }



}
