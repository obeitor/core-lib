package com.softobt.core.handlers;
/**
 * @author aobeitor
 * @since 05/15/19
 */
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ComponentScan
@RestController
public class ErrorHandler implements ErrorController{


    private static final String STATUS_CODE = "javax.servlet.error.status_code";
    private static final String EXCEPTION = "javax.servlet.error.exception";
    public static final String PATH_ATTRIBUTE = "com.softobt.path";

    @RequestMapping("/error")
    public void handleError(HttpServletRequest request)throws Exception{
        int statusCode = request.getAttribute(STATUS_CODE)!=null ?
                (int)request.getAttribute(STATUS_CODE) : 500;
        if(statusCode == HttpServletResponse.SC_NOT_FOUND){
            throw new NoHandlerFoundException(request.getMethod(),(String)request.getAttribute(PATH_ATTRIBUTE),null);
        }
        if(request.getAttribute(EXCEPTION)!=null)
            throw (Exception)request.getAttribute(EXCEPTION);
        throw new Exception("Exception cause unknown!");

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
