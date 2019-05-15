package com.softobt.exceptions.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softobt.core.models.ApiResponse;

public class ApiErrorResponse implements ApiResponse {
    private RestControllerException restControllerException;
    public ApiErrorResponse(RestControllerException rce) {
        this.restControllerException = rce;
    }

    @JsonProperty("code")
    public int getCode() {
        return restControllerException.getCode();
    }

    @JsonProperty("description")
    public String getDescription() {
        return restControllerException.getMessage();
    }

    @Override
    @JsonProperty("success")
    public boolean getSuccess() {
        return false;
    }

    @Override
    @JsonProperty("response")
    public Object getResponse() {
        return new Object();
    }
}
