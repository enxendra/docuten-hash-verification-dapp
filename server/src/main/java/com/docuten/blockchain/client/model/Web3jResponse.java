package com.docuten.blockchain.client.model;

import java.util.HashMap;

public class Web3jResponse {

    private Boolean hasError;
    private String error;
    private HashMap<String, Object> data;

    public Web3jResponse() {}

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
