package com.docuten.blockchain.client.model;

public class SmartContractResponse {

    private final long id;
    private final String content;

    public SmartContractResponse(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
