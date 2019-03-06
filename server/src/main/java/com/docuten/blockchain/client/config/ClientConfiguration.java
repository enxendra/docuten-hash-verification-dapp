package com.docuten.blockchain.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ClientConfiguration {

    private static Logger log = Logger.getLogger( ClientConfiguration.class.getName() );

    @Value("${endpoint.alastria.telsius}")
    public String ALASTRIA_NODE_ENDPOINT;

    @Value("${contract.address.alastria.telsius}")
    public String ALASTRIA_CONTRACT_ADDRESS;

    @Value("${wallet.raw.address}")
    public String MY_ADDRESS;


}
