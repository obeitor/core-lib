package com.softobt.exceptions.handlers;

import com.softobt.core.models.ApiResponse;
import com.softobt.exceptions.models.ApiErrorResponse;
import com.softobt.exceptions.models.RestControllerException;
import com.softobt.exceptions.models.RestServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestControllerException.class)
    @ResponseBody
    public final ApiResponse handleRestControllerException(RestControllerException ex){
        return new ApiErrorResponse(ex);
    }

    @ExceptionHandler(RestServiceException.class)
    @ResponseBody
    public final ApiResponse handleRestServiceException(RestServiceException ex){
        return new ApiErrorResponse(new RestControllerException(ex));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public final ApiResponse handleRuntimeExceptions(){
        return new ApiErrorResponse(new RestControllerException());
    }
}
