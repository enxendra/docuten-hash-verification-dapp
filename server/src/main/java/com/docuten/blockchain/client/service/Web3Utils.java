package com.docuten.blockchain.client.service;

import com.docuten.blockchain.client.config.ClientConfiguration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.math.BigInteger;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class Web3Utils {

    private ClientConfiguration clientConfiguration;

    @Autowired
    public Web3Utils(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    // Retrieves the address of the deployed contract for one network
    // according to our configuration.
    public String getContractAddress(String network) {
        switch (network){
            case "alastria":
                return clientConfiguration.ALASTRIA_CONTRACT_ADDRESS;
            default:
                return clientConfiguration.ALASTRIA_CONTRACT_ADDRESS;
        }
    }

    // Retrieves the endpoint of the one node of a specific network
    // according to our configuration.
    public String getNetworkEndpoint(String network) {
        switch (network){
            case "alastria":
                return clientConfiguration.ALASTRIA_NODE_ENDPOINT;
            default:
                return clientConfiguration.ALASTRIA_NODE_ENDPOINT;
        }
    }
    // Retrieves the gas price for a specific network
    public BigInteger getGasPrice(String network) {
        switch (network){
            case "alastria":
                return new BigInteger("0");
            default:
                return Contract.GAS_PRICE;
        }
    }

    public byte[] hexStringToByteArray(String hexString){
        byte[] myStringInByte = Numeric.hexStringToByteArray(hexString);
        return myStringInByte;
    }

    public String getMyAddress() {
       return clientConfiguration.MY_ADDRESS;
    }

}
