package com.docuten.blockchain.client.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Document {

    @NotBlank
    @Size(min = 1, max = 32)
    @ApiModelProperty(notes = "Sha256 hash of a document. No two documents can have the same hash.", example = "57136f0a3d87e187624c0adb30ff2fbdcf47ac9613b1ba46b870e57fa3b5f89c", required = true)
    private String documentHash;

    @ApiModelProperty(notes = "IPFS Hash (optional) in case the document has been stored there", example = "QmW1yH3Q8fXCbvbctjCVodCtg7kowsGYRzUTDkGBCe1AVo")
    private String ipfsHash;

    @ApiModelProperty(notes = "Timestamp with the date of the document creation (yyyy-MM-dd HH:mm:ss)", example = "2019-12-01 12:00:00")
    private String timestamp;

    public String getDocumentHash() {
        if(documentHash == null)
            return "";

        return documentHash;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getIpfsHash() {
        if(ipfsHash == null)
            return "";

        return ipfsHash;
    }

    public void setIpfsHash(String ipfsHash) {
        this.ipfsHash = ipfsHash;
    }

    public String getTimestamp() {
        if(timestamp == null)
            return "";

        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
