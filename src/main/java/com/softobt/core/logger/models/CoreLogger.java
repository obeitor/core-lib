package com.softobt.core.logger.models;

import org.slf4j.LoggerFactory;

import java.util.logging.Logger;


public class CoreLogger {
    private static final String SERVICE = "SERVICE_LOG",
    ERROR = "ERROR_LOG";
    public static Logger getServiceLog(){
        return Logger.getLogger(SERVICE);
    }

    public static org.slf4j.Logger getErrorLog(){
        return LoggerFactory.getLogger(ERROR);
    }
}
