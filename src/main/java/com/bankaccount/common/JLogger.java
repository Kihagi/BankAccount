package com.bankaccount.common;

import org.jboss.logging.Logger;

/**
 * Created by Mathenge on 8/16/2017.
 */
public class JLogger<T> {

    private final Class<T> clazz;
    public JLogger(Class clazz)
    {
        this.clazz = clazz;
    }

    /**
     * helper class d() to log debug level information.
     */

    private static String format_msg(String message)
    {
        return ">>>>>>>>>>> " + message + " <<<<<<<<<<";
    }
    public void d(String message)
    {
        Logger log = Logger.getLogger(this.clazz);
        log.debug(format_msg(message));
    }


    /**
     * helper class i() to log info level information.
     */
    public void i(String message)
    {
        Logger log = Logger.getLogger(this.clazz);
        log.info(format_msg(message));
    }


    /**
     * helper class w() to log warning level information.
     */
    public void w(String message)
    {
        Logger log = Logger.getLogger(this.clazz);
        log.warn(format_msg(message));
    }



    /**
     * helper class e() to log error information.
     */
    public void e(String message)
    {
        Logger log = Logger.getLogger(this.clazz);
        log.error(format_msg(message));
    }



    /**
     * helper class f() to log fatal level information.
     */
    public void f(String message)
    {
        Logger log = Logger.getLogger(this.clazz);
        log.fatal(format_msg(message));
    }



    /**
     * helper class t() to log trace information.
     */
    public void t(String message)
    {
        Logger log = Logger.getLogger(this.clazz);
        log.trace(format_msg(message));
    }

}