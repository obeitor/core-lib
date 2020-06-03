package com.softobt.core.exceptions.enums;
/**
 * @author aobeitor
 * @since 05/15/19
 */
public enum ErrorCode {
    UNKNOWN_ERROR(100),
    BAD_PATH_PARAM(20),
    BAD_REQUEST_PARAM(21),
    BAD_AUTHORIZATION(22),
    END_POINT_DOWN(30),
    ALREADY_EXIST(40),
    RESOURCE_NOT_FOUND(41);

    private int value;
    private ErrorCode(int val){
        this.value = val;
    }
    public int getValue(){
        return this.value;
    }
}
