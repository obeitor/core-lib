package com.softobt.core.handlers;
/**
 * @author aobeitor
 * @since 05/15/19
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softobt.core.api.ApiResponseEnvelop;
import com.softobt.core.exceptions.enums.ErrorCode;
import com.softobt.core.exceptions.models.ApiErrorResponse;
import com.softobt.core.exceptions.models.RestControllerException;
import com.softobt.core.exceptions.models.RestServiceException;
import com.softobt.core.logger.services.LoggerService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Iterator;

@ControllerAdvice
public class RestControllerAdvice implements ResponseBodyAdvice<Object> {

    LoggerService loggerService;


    @ExceptionHandler(RestControllerException.class)
    public ResponseEntity<ApiErrorResponse> handleRestControllerException(RestControllerException ex){
        loggerService.warn(RestControllerException.class,ex.getMessage());
        return new ResponseEntity<>(new ApiErrorResponse(ex), HttpStatus.OK);
    }

    @ExceptionHandler(RestServiceException.class)
    public ResponseEntity<ApiErrorResponse> handleRestServiceException(RestServiceException ex){
        loggerService.warn(RestServiceException.class,ex.getMessage());
        return new ResponseEntity<>(new ApiErrorResponse(new RestControllerException(ex)),HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeExceptions(RuntimeException ex){
        loggerService.severe(ex);
        return new ResponseEntity<>(new ApiErrorResponse(new RestControllerException()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodNotSupported(MethodArgumentNotValidException ex){
        LoggerService.warn(MethodArgumentNotValidException.class,ex.getMessage());
        String errMsg  = "Request Error ";
        Iterator fieldErrors = ex.getBindingResult().getFieldErrors().iterator();
        while(fieldErrors.hasNext()){
            FieldError fieldError = (FieldError)fieldErrors.next();
            errMsg+=", "+fieldError.getField()+"::"+fieldError.getDefaultMessage();
        }
        return new ResponseEntity<>(new ApiErrorResponse(new RestControllerException(errMsg)),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex){
        LoggerService.warn(HttpRequestMethodNotSupportedException.class,ex.getMessage());
        return new ResponseEntity<>(new ApiErrorResponse(new RestControllerException(ex.getMessage())),HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNoHandlerFoundExceptions(NoHandlerFoundException ex){
        loggerService.warn(NoHandlerFoundException.class,ex.getHttpMethod()+"::"+ex.getRequestURL());
        return new ResponseEntity<>(new ApiErrorResponse(new RestControllerException("You seem to have requested a non existent resource", ErrorCode.RESOURCE_NOT_FOUND)),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleExceptions(Exception ex){
        loggerService.severe(ex);
        return new ResponseEntity<>(new ApiErrorResponse(new RestControllerException()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ApiResponseEnvelop env = o instanceof ApiResponseEnvelop ? (ApiResponseEnvelop) o : new ApiResponseEnvelop(o);
        if(o instanceof String){

            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(env);
            }
            catch (JsonProcessingException e){
                return o;
            }
        }
        return env;
    }

    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
}
