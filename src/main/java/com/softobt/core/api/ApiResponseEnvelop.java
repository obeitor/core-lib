package com.softobt.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softobt.core.logger.services.LoggerService;

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


    public class StringValue{
        private String value;

        public StringValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
