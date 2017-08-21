package com.bankaccount.controller;

import com.bankaccount.common.Helper;
import com.bankaccount.common.JLogger;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mathenge on 8/16/2017.
 */
public class BaseServlet extends HttpServlet {
    private final JLogger jLogger = new JLogger(this.getClass());
    final Helper helper = new Helper();

    String get(HttpServletRequest req, String param)
    {
        return helper.toString(req.getParameter(param));
    }


    void respond(HttpServletResponse resp, boolean status, String message, JSONObject json)
    {
        if(json != null)
            jLogger.i("Object is : " + json.toString());
        try {
            String response = helper.result(status, message, json);
            jLogger.i(response);
            resp.getWriter().write(response);
        } catch (IOException ioe) {
            jLogger.e("Problem sending response" + ioe.getMessage());
        }

    }

    String getSessKey(HttpServletRequest req, String param)
    {
        HttpSession session = req.getSession(false);
        return session != null ? helper.toString(session.getAttribute(param)) : null;
    }
    void redirect(HttpServletResponse resp, String page)
    {
        try {
            resp.sendRedirect(getServletContext().getContextPath() + page);
        } catch (IOException ioe) {
            jLogger.e("Sorry, we have an io exception: " + ioe.getMessage());
        }
    }
}
