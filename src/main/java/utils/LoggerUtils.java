package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    public static Logger createLogger ( Class clazz ) {
        return LoggerFactory.getLogger(clazz);
    }

    public static Logger createLogger ( String clazz ) {
        return LoggerFactory.getLogger(clazz);
    }
}
