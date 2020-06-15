package com.softobt.core.exceptions.models;

/**
 * @author aobeitor
 * @since 6/3/20
 */
public class CredentialException extends Exception {
    private CredentialExceptionType code;
    public CredentialException(CredentialExceptionType type) {
        super(type==null ? CredentialExceptionType.DEFAULT_ERROR.getMsg():type.getMsg());
        this.code = type==null ? CredentialExceptionType.DEFAULT_ERROR : type;
    }

    public CredentialException(String msg){
        super(msg);
        this.code = CredentialExceptionType.DEFAULT_ERROR;
    }

    public enum CredentialExceptionType{
        UNKNOWN_USER(1,"User does not exist"),
        INVALID_PASSWORD(2,"Invalid username and password"),
        INVALID_TOKEN(3,"Invalid Token"),
        EXPIRED_TOKEN(4,"Expired Token"),
        DEFAULT_ERROR(0,"Failed to validate credential"),
        NOT_AUTHORIZED(5,"User not authorized to perform operation");

        private int code;
        private String msg;

        private CredentialExceptionType(int code, String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
