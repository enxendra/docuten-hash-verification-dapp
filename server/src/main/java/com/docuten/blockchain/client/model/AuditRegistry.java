package com.docuten.blockchain.client.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@ApiModel(description = "Class representing an audit registry tracked by the application.")
public class AuditRegistry {

    @NotBlank
    @Size(min = 1, max = 32)
    @ApiModelProperty(notes = "Sha256 hash of a document. No two documents can have the same hash.", example = "57136f0a3d87e187624c0adb30ff2fbdcf47ac9613b1ba46b870e57fa3b5f89c")
    private String documentHash;

    @ApiModelProperty(notes = "Description of the event in JSON format", example = "statusChange : 'REJECTED'")
    private String eventDescription;

    @ApiModelProperty(notes = "Timestamp of the event (system internal time with format yyyy-MM-dd HH:mm:ss)", example = "2019-01-22 13:51:49")
    private String timestamp;

    @ApiModelProperty(notes = "Blocktime (block timestamp in UNIX time)", example = "1548161509")
    private BigInteger blocktime;

    public BigInteger getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(BigInteger blocktime) {
        this.blocktime = blocktime;
    }

    public String getDocumentHash() {
        return documentHash;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
