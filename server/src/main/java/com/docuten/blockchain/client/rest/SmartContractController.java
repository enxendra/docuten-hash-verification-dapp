package com.docuten.blockchain.client.rest;

import com.docuten.blockchain.client.model.Web3jResponse;
import com.docuten.blockchain.client.service.SmartContractService;
import com.docuten.blockchain.client.service.Web3jService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SmartContractController {

    static Logger logger = LoggerFactory.getLogger(SmartContractService.class);
    private SmartContractService smartContractService;
    private Web3jService web3jService;

    @Autowired
    public SmartContractController(SmartContractService smartContractService, Web3jService web3jService) {
        this.smartContractService = smartContractService;
        this.web3jService = web3jService;
    }

    @RequestMapping("/documentDetails")
    @ApiOperation("Returns the details of a certified document, including a history of audit registries")
    public Web3jResponse getDocumentDetailsByHash(  @RequestHeader(value="network", defaultValue="alastria") String network,
                                                    @RequestParam(value="documentHash") String documentHash) {
        logger.info("REST Request | GET | /documentDetails");
        return smartContractService.getDocumentDetailsByHash(network, documentHash, true);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation("It returns a summary of the status of the contract")
    public Web3jResponse getContractInfo(   @RequestHeader(value="network", defaultValue="alastria") String network) {
        logger.info("REST Request | GET | /contractInfo");
        return smartContractService.getContractInfo(network);
    }

}
