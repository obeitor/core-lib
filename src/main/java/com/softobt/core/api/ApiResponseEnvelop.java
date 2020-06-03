package com.softobt.core.api;
/**
 * @author aobeitor
 * @since 05/15/19
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softobt.core.logger.services.LoggerService;

import java.time.LocalDateTime;

public class ApiResponseEnvelop implements ApiResponse{

    private Object response;

    public ApiResponseEnvelop(Object response) {
        this.response = response;
    }

    @Override
    @JsonProperty("success")
    public boolean getSuccess() {
        return true;
    }

    @Override
    @JsonProperty("response")
    public Object getResponse() {
        return response;
    }

    @JsonProperty("responseType")
    public ResponseType getType(){
        if(this.response == null){
            return ResponseType.EMPTY;
        }
        if(this.response instanceof LocalDateTime){
            return ResponseType.DATETIME;
        }
        if(this.response instanceof String){
            return ResponseType.TEXT;
        }
        if(this.response instanceof Number){
            return ResponseType.NUMBER;
        }
        if(this.response instanceof Boolean){
            return ResponseType.BOOLEAN;
        }
        if(this.response instanceof Iterable){
            return ResponseType.LIST;
        }
        return ResponseType.OBJECT;
    }

}
