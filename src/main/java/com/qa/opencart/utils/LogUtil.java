package com.qa.opencart.utils;


import com.qa.opencart.factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    public static Logger log = LogManager.getLogger(DriverFactory.class);

    public static void info(String msg) {
        log.info(msg);
    }

    public static void warn(String msg) {
        log.warn(msg);
    }

    public static void error(String msg) {
        log.error(msg);
    }

    public static void fatal(String msg) {
        log.fatal(msg);
    }


}
