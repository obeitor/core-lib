package com.softobt.core.exceptions.models;
/**
 * @author aobeitor
 * @since 05/15/19
 */
public class RestServiceException extends Exception{

    private int code;

    public int getCode() {
        return code;
    }

    public RestServiceException(){

    }

    public RestServiceException(String msg){
        super(msg);
    }

    public RestServiceException(String msg, int code){
        super(msg);
        this.code = code;
    }
}
