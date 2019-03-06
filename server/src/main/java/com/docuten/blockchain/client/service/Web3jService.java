package com.docuten.blockchain.client.service;

import com.docuten.blockchain.client.model.Web3jResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.HashMap;

@Service
public class Web3jService {

    protected Web3Utils web3Utils;

    @Autowired
    public Web3jService(Web3Utils web3Utils) {
        this.web3Utils = web3Utils;
    }

}
