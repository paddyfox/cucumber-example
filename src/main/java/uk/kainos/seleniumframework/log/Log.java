package uk.kainos.seleniumframework.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    public static void Info(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.info(message);
    }

    public static void Error(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.error(message);
    }

    public static void Warn(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.warn(message);
    }

    public static void Debug(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.debug(message);
    }

    private static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Log.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }
        }
        return null;
    }
}
