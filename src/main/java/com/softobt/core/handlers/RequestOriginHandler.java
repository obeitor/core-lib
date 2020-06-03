package com.softobt.core.handlers;
/**
 * @author aobeitor
 * @since 05/15/19
 */
import com.softobt.core.logger.services.LoggerService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestOriginHandler extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getAttribute(ErrorHandler.PATH_ATTRIBUTE)==null) {
            LoggerService.requestInfo(request);
            request.setAttribute(ErrorHandler.PATH_ATTRIBUTE, request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
    }
}
