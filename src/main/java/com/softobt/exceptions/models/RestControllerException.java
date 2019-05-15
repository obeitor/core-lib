package com.softobt.exceptions.models;


import com.softobt.exceptions.enums.ErrorCode;

public class RestControllerException extends Exception {
    /**
     * Default Error Message
     */
    public static final String DEF_ERR_MSG = "An unknown error just occurred, we are fixing!";
    private int code;
    public RestControllerException(){
        super(DEF_ERR_MSG);
        this.code = ErrorCode.UNKNOWN_ERROR.getValue();
    }
    public RestControllerException(String msg){
        super(msg);
        this.code = ErrorCode.UNKNOWN_ERROR.getValue();
    }
    public RestControllerException(String msg, ErrorCode code){
        super(msg);
        this.code = code.getValue();
    }
    private RestControllerException(String msg, int code){
        super(msg);
        this.code = code;
    }
    public RestControllerException(Throwable cause){
            this(cause instanceof RestServiceException ? cause.getMessage():DEF_ERR_MSG,
                    cause instanceof RestServiceException ? ((RestServiceException) cause).getCode() : ErrorCode.UNKNOWN_ERROR.getValue());
    }

    public int getCode() {
        return code;
    }
}
