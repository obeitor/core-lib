package com.softobt.core.exceptions.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softobt.core.api.ApiResponse;
import com.softobt.core.api.ApiResponseEnvelop;

public class ApiErrorResponse extends ApiResponseEnvelop {
    private RestControllerException restControllerException;
    public ApiErrorResponse(RestControllerException rce) {
        super(null);
        this.restControllerException = rce;
    }

    @JsonProperty("error")
    public int getCode() {
        return restControllerException.getCode();
    }

    @JsonProperty("message")
    public String getDescription() {
        return restControllerException.getMessage();
    }

    @Override
    @JsonProperty("success")
    public boolean getSuccess() {
        return false;
    }

}
