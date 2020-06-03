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
}
