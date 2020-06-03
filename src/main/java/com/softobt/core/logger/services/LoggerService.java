package com.softobt.core.logger.services;
/**
 * @author aobeitor
 * @since 05/15/19
 */
import com.softobt.core.logger.models.CoreLogger;

import javax.servlet.http.HttpServletRequest;

public class LoggerService {

    public static void requestInfo(HttpServletRequest request){
        CoreLogger.getServiceLog().info("[INCOMING] -> ["+request.getRemoteAddr()+"] ["+request.getRequestURI()+"]");
    }

    public static void info(Class clazz, String info){
        CoreLogger.getServiceLog().info(clazz.getSimpleName()+"::"+info);
    }
    public static void warn(Class clazz, String warning){
        CoreLogger.getServiceLog().warning(clazz.getSimpleName()+"::"+warning);
    }
    public static void severe(Exception ex, String msg){
        CoreLogger.getErrorLog().error(msg,ex);
    }
    public static void severe(Exception ex){
        CoreLogger.getErrorLog().error(ex.getMessage(),ex);
    }
}
