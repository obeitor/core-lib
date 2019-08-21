package com.softobt.core.exceptions.models;

public class RestServiceException extends Exception{
    private int code;

    public int getCode() {
        return code;
    }
}
