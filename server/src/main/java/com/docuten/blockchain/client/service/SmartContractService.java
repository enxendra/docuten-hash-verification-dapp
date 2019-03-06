package com.docuten.blockchain.client.service;

import com.docuten.blockchain.client.model.AuditRegistry;
import com.docuten.blockchain.client.model.Web3jResponse;
import com.docuten.blockchain.client.wrapper.ProofOfLifeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.net.ConnectException;
import java.util.HashMap;

@Service
public class SmartContractService extends  Web3jService{

    @Autowired
    public SmartContractService(Web3Utils web3Utils) {
        super(web3Utils);
    }

    static Logger logger = LoggerFactory.getLogger(SmartContractService.class);

    public Web3jResponse getContractInfo(String network){

        Web3jResponse response = new Web3jResponse();

        try {

            // Initialize contract address and network endpoint:
            String contractAddress = web3Utils.getContractAddress(network);
            String networkEndpoint = web3Utils.getNetworkEndpoint(network);
            BigInteger gasPrice = web3Utils.getGasPrice(network);

            logger.info("New Web3 instance");
            logger.info("Function: getContractInfo");
            logger.info("Network endpoint: " + networkEndpoint);
            logger.info("Contract address: " + contractAddress);

            // Initialize web3 instance pointing to specific blockchain network
            Web3j web3 = Web3j.build(new HttpService(networkEndpoint));

            // Define transaction manager with our user address (we don't need our private key for a reading operation)
            TransactionManager manager = new ReadonlyTransactionManager(web3, web3Utils.getMyAddress());
            logger.info("Caller address: " + web3Utils.getMyAddress());

            // Load contract wrapper using the address of the deployed instance in the specified network
            ProofOfLifeProxy contract = ProofOfLifeProxy.load(contractAddress, web3, manager, gasPrice, Contract.GAS_LIMIT);

            // Return formated information
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("contractOwner", contract.contractOwner().send());
            data.put("proxyAddress", contract.getContractAddress());
            data.put("delegateCallAddress", contract.delegateCallAddress().send());
            data.put("isLocked", contract.isLocked().send());
            data.put("openToEveryUser", contract.isOpenToEveryUser().send());
            data.put("documentsNumber", contract.lastId().send());

            logger.info("\nResult:");
            logger.info(data.toString());

            // Add data to web service response
            response.setData(data);
            response.setHasError(false);

        } catch (ConnectException e1){
            logger.info("Result:");
            e1.printStackTrace();
            response.setHasError(true);
            response.setError("Connection error: Verify that your blockchain node is up and running!");
        } catch (Exception e){
            logger.info("Result:");
            e.printStackTrace();
            response.setHasError(true);
            response.setError(e.getMessage());
        }
        return response;
    }

    public Web3jResponse getDocumentDetailsByHash(String network, String documentHash, Boolean throwErrorIfDoesNotExist){

        Web3jResponse response = new Web3jResponse();

        try {

            // Initialize contract address and network endpoint:
            String contractAddress = web3Utils.getContractAddress(network);
            String networkEndpoint = web3Utils.getNetworkEndpoint(network);
            BigInteger gasPrice =  web3Utils.getGasPrice(network);

            logger.info("New Web3 instance");
            logger.info("Function: getDocumentDetailsByHash");
            logger.info("Network endpoint: "+networkEndpoint);
            logger.info("Contract address: "+contractAddress);

            // Initialize web3 instance pointing to specific blockchain network
            Web3j web3 = Web3j.build(new HttpService(networkEndpoint));

            // Define transaction manager with our user address (we don't need our private key for a reading operation)
            TransactionManager manager = new ReadonlyTransactionManager(web3, web3Utils.getMyAddress());
            logger.info("Caller address: "+web3Utils.getMyAddress());

            // Load contract wrapper using the address of the deployed instance in the specified network
            ProofOfLifeProxy contract = ProofOfLifeProxy.load(contractAddress, web3, manager, gasPrice,  Contract.GAS_LIMIT);

            // Call the contract:
            // Retrieve document details through the function getDocumentDetailsByHash
            RemoteCall<Tuple4<BigInteger, byte[], String, String>> remoteCall = contract.getDocumentDetailsByHash(web3Utils.hexStringToByteArray(documentHash));
            Tuple4<BigInteger, byte[], String, String> contractResponse = remoteCall.send();

            logger.info(contractResponse.toString());
            // Stop web3 instance
            web3.shutdown();

            // Return error if document not found
            if(throwErrorIfDoesNotExist && contractResponse.getValue1().intValue() <= 0) throw new Exception("There is not any document with that hash.");

            // Retrieve also history information (audit registries)
            logger.info("How many audit registries has the document with hash "+documentHash+"?");
            BigInteger numberOfAuditRegistries = countAuditRegistriesByDocumentHash(contractAddress, networkEndpoint, gasPrice, documentHash);
            logger.info("Number: "+numberOfAuditRegistries+"\n");

            AuditRegistry[] auditRegistries = new AuditRegistry[numberOfAuditRegistries.intValue()];
            for(int i = 0; i < numberOfAuditRegistries.intValue(); i++) {
                BigInteger index = BigInteger.valueOf(i);
                auditRegistries[i] = getAuditRegistryByDocumentHash(contractAddress, networkEndpoint, gasPrice, documentHash, index);
            }

            // Transform contract response into a well formatted data for our webservice, including auditRegistries
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("id", contractResponse.getValue1() != null ?  contractResponse.getValue1().toString() : "");
            data.put("docHash", documentHash);
            data.put("ipfsHash", contractResponse.getValue3() != null ?  contractResponse.getValue3() : "");
            data.put("documentOwner", contractResponse.getValue4() != null ?  contractResponse.getValue4() : "");
            data.put("auditRegistries", auditRegistries);

            logger.info("\nResult:");
            logger.info(data.toString());

            // Add data to web service response
            response.setData(data);
            response.setHasError(false);

        } catch (ConnectException e1){
            logger.info("Result:");
            e1.printStackTrace();
            response.setHasError(true);
            response.setError("Connection error: Verify that your blockchain node is up and running!");
        } catch (Exception e){
            logger.info("Result:");
            e.printStackTrace();
            response.setHasError(true);
            response.setError(e.getMessage());
        }
        return response;
    }

    public BigInteger countAuditRegistriesByDocumentHash(String contractAddress, String networkEndpoint, BigInteger gasPrice, String documentHash) throws Exception {

        Web3jResponse response = new Web3jResponse();

            logger.info("\tNew Web3 instance");
            logger.info("\tFunction: countAuditRegistriesByDocumentHash");

            // Initialize web3 instance pointing to specific blockchain network
            Web3j web3 = Web3j.build(new HttpService(networkEndpoint));

            // Define transaction manager with our user address (we don't need our private key for a reading operation)
            TransactionManager manager = new ReadonlyTransactionManager(web3, web3Utils.getMyAddress());
            logger.info("\tCaller address: "+web3Utils.getMyAddress().substring(0,5)+"...");

            // Load contract wrapper using the address of the deployed instance in the specified network
            ProofOfLifeProxy contract = ProofOfLifeProxy.load(contractAddress, web3, manager, gasPrice,  Contract.GAS_LIMIT);

            // Call the contract:
            // Retrieve document details through the function countAuditRegistriesByDocumentHash
            RemoteCall<BigInteger> remoteCall = contract.countAuditRegistriesByDocumentHash(web3Utils.hexStringToByteArray(documentHash));
            BigInteger numberOfAuditRegistries = remoteCall.send();

            // Stop web3 instance
            web3.shutdown();

            return numberOfAuditRegistries;
    }

    private AuditRegistry getAuditRegistryByDocumentHash(String contractAddress, String networkEndpoint, BigInteger gasPrice, String documentHash, BigInteger index) throws Exception {

        Web3jResponse response = new Web3jResponse();

        logger.info("\t\tNew Web3 instance");
        logger.info("\t\tFunction: getAuditRegistryByDocumentHash");

        // Initialize web3 instance pointing to specific blockchain network
        Web3j web3 = Web3j.build(new HttpService(networkEndpoint));

        // Define transaction manager with our user address (we don't need our private key for a reading operation)
        TransactionManager manager = new ReadonlyTransactionManager(web3, web3Utils.getMyAddress());
        logger.info("\t\tCaller address: "+web3Utils.getMyAddress().substring(0,5)+"...");

        // Load contract wrapper using the address of the deployed instance in the specified network
        ProofOfLifeProxy contract = ProofOfLifeProxy.load(contractAddress, web3, manager, gasPrice,  Contract.GAS_LIMIT);

        // Call the contract:
        // Retrieve document details through the function getAuditRegistryByDocumentHash
        RemoteCall<Tuple3<String, String, BigInteger>> remoteCall = contract.getAuditRegistryByDocumentHash(web3Utils.hexStringToByteArray(documentHash), index);
        Tuple3<String, String, BigInteger> remoteCallResponse = remoteCall.send();

        AuditRegistry auditRegistry = new AuditRegistry();
        auditRegistry.setDocumentHash(documentHash);
        auditRegistry.setEventDescription(remoteCallResponse.getValue1());
        auditRegistry.setTimestamp(remoteCallResponse.getValue2());
        auditRegistry.setBlocktime(remoteCallResponse.getValue3());

        // Stop web3 instance
        web3.shutdown();

        return auditRegistry;
    }

    private BigInteger getDocumentIdFromHash(String network, String documentHash) throws Exception {
        Web3jResponse response = getDocumentDetailsByHash(network, documentHash, false);

        if(response.getHasError()){
            throw new Exception(response.getError());
        }

        return new BigInteger((String) response.getData().get("id"));
    }

}
